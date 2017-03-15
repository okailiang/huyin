package press.wein.home.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import press.wein.home.constant.Constants;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.CookieManager;
import press.wein.home.model.bo.UserSession;
import press.wein.home.redis.RedisClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 用户登录拦截器
 *
 * @author oukailiang
 * @create 2017-02-22 下午4:28
 */
public class LoginInterceptor implements HandlerInterceptor

{

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

//        @Autowired
//        private RedisTemplate<String, String> template;

    @Autowired
    private RedisClient baseRedisProxy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //crossResponse(response);
        /** 是否需要验证登录 */
        if (isAuthFilter(request)) {
            if (checkSessionCookieExists(request, response) && setApplicationContext(request)) {
                /** 验证登录成功 */
                return true;
            } else {
                /** 验证登录失败，重定向到登录页面 */
                String basePath = "";
                if ("post".equalsIgnoreCase(request.getMethod())) {
                    String retUrl = request.getHeader("Referer");
                    if (retUrl != null) {
                        basePath = (retUrl);
                    }
                }
                if (StringUtils.isEmpty(basePath)) {
                    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                            + request.getContextPath() + request.getRequestURI();
                    if (request.getQueryString() != null) {
                        basePath = basePath + "?" + request.getQueryString();
                    }
                }
                response.sendRedirect(request.getContextPath() + Constants.LOGIN_PAGE_URI + "?redirectUrl=" + URLEncoder.encode(basePath, "UTF-8"));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * Cross-origin resource sharing (CORS)
     *
     * @param response
     */
    private void crossResponse(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", Constants.HTTP_HEADERS);
    }

    /**
     * <pre>
     * 是否需要验证登录
     * </pre>
     *
     * @param request
     * @return
     */
    private boolean isAuthFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (String e : Constants.getNotAuthFilterURIList()) {
            LOG.info(request.getContextPath());
            if (uri.startsWith(request.getContextPath() + e)) {
                return false;
            }
        }
        return true;
    }

    private boolean setApplicationContext(HttpServletRequest request) {
        UserSession backOperatorSessionCache;
        // 本地服务器端的session被销毁(应用服务器被重启)
        CookieManager cookieManager = new CookieManager(request, null);
        String cookieValue = cookieManager.getCookieValue(Constants.CREDIT_USERINFO_COOKIE_NAME);
        // 如果cookie的value为空，LoginInterceptor会跳转到登录页面
        if (StringUtils.isNotBlank(cookieValue)) {
            // 通过key从memcached中获取session信息
            Object obj = baseRedisProxy.get(cookieValue, UserSession.class);
            // memcached中session信息丢失了(memcached服务器被重启)
            if (null == obj) {
                // 可能缓存过期，或者memcached重起
                LOG.warn("The cache is expired or restarted!");
                return false;
            } else {
                backOperatorSessionCache = (UserSession) obj;
                request.getSession().setAttribute("user", backOperatorSessionCache);
                ApplicationUserContext.setUser(backOperatorSessionCache);
            }
        }

        return true;
    }

    private boolean checkSessionCookieExists(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CookieManager cookieManager = new CookieManager(httpServletRequest, httpServletResponse);
        String cookieValue = cookieManager.getCookieValue(Constants.CREDIT_USERINFO_COOKIE_NAME);
        // cookie被清除
        if (StringUtils.isBlank(cookieValue)) {
            return false;
        }
        Object obj = baseRedisProxy.get(cookieValue, UserSession.class);
        if (obj == null) {
            return false;
        }
        UserSession session = (UserSession) obj;
        Date oldDate = session.getLastExtension();

        long oldLoginTime = oldDate.getTime();
        long currentTime = new Date().getTime();
        // 会话cookie过期时间 (60分钟)
        long expiretime = Constants.SESSION_EXPIRE * 60 * 1000L;
        long halfExpiretime = expiretime / 2;
        // 当前时间减去最新登陆时间
        long leftTime = currentTime - oldLoginTime;
        // 用户开着浏览器60分钟一直不用，之后再用就要跳到登陆页面
        if (expiretime < leftTime) {
            return false;
        }
        // 如果用户使用系统过了过期时间的一半，对会话cookie进行续命
        if (halfExpiretime <= leftTime) {
            session.setLastExtension(new Date());
            baseRedisProxy.set(cookieValue, session, Constants.SESSION_EXPIRE);
        }
        return true;
    }
}