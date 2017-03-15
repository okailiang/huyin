package press.wein.home.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.CookieManager;
import press.wein.home.common.SysConfigProperty;
import press.wein.home.constant.Constants;
import press.wein.home.model.User;
import press.wein.home.model.UserInfo;
import press.wein.home.model.vo.UserLoginVo;
import press.wein.home.redis.RedisClient;
import press.wein.home.service.LoginService;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * 用户登录controller
 *
 * @author oukailiang
 * @create 2017-02-22 下午4:15
 */
@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private Producer captchaProducer;

    /**
     * 用户登录
     *
     * @param userLoginVo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(UserLoginVo userLoginVo, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("1Hello world");
//        user.setUserName("小明1");
//        user.setCreateTime(new Date());
//        user.setCreator("小红32");
//        user.setEmail("123@163.com");
//
//        String log = SysConfigProperty.getProperty("wein.log");
//        LOG.info("wein log:" + log);
//        redisClient.set("user", user, 10);
//        User user1 = redisClient.get("user", User.class);
//        LOG.info(JSON.toJSONString(user));
        //从缓存种获取登录错误次数
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        if(StringUtils.isNotBlank(email)){
//            userLoginVo.setEmail(email);
//        }

        Integer times = 0;
        boolean checkFlag = userLoginVo.isCheckFlag();
        //userLoginVo.setUser(user);
        LOG.info("login pram:" + JSON.toJSONString(userLoginVo));
        try {
            times = redisClient.get(Constants.USER_ERROR_TIMES + "_" + userLoginVo.getAccount(), Integer.class);
        } catch (Exception e) {
            LOG.error("get usr login error times error!", e.getMessage());
        }
        if (times != null && times >= 3) {
            checkFlag = true;
        } else {
            checkFlag = false;
        }
        return JSON.toJSONString(loginService.login(userLoginVo, request, response));
    }

    /**
     * 退出页面
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {

        CookieManager cookieManager = new CookieManager(request, response);
        if (ApplicationUserContext.getUser() != null) {
            String cookieValue = cookieManager.getCookieValue(Constants.CREDIT_USERINFO_COOKIE_NAME);
            // 删除redis缓存
            redisClient.delete(cookieValue);
            // 删除系统的本地session
            ApplicationUserContext.clear();
            // 删除用户cookie
            cookieManager.setCookie(Constants.CREDIT_USERINFO_COOKIE_NAME, null,
                    SysConfigProperty.getProperty(Constants.DOMAIN_NAME), 0);
        }
        response.setHeader("progma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String userName = cookieManager.getCookieValue(Constants.CREDIT_LOGIN_COOKIE_NAME);
        model.addAttribute("userName", userName);
        return "login";
    }

    /**
     * 跳转到登陆页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model) {

        CookieManager cookieManager = new CookieManager(request, response);
        String userName = cookieManager.getCookieValue(Constants.CREDIT_LOGIN_COOKIE_NAME);
        model.addAttribute("userName", userName);

        model.addAttribute("googleAnalyticsId", SysConfigProperty.getProperty(Constants.GOOGLE_ANALYTICS_ID));
        String redirectUrl = request.getParameter("redirectUrl");
        if (!StringUtils.isEmpty(redirectUrl)) {
            if (redirectUrl.contains(SysConfigProperty.getProperty(Constants.DOMAIN_NAME))) {
                model.addAttribute("redirectUrl", redirectUrl);
            } else {
                LOG.info(" redirectUrl not cantains wein.press ,redirectUrl : " + redirectUrl);
            }
        }
        return "/login";
    }

    /**
     * 默认页面
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }


    /**
     * 获取验证码
     *
     * @param response
     * @param request
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = {"/kaptcha", "/admin/kaptcha"})
    public void kaptcha(HttpServletResponse response, HttpServletRequest request,
                        HttpSession session) throws IOException {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpg");

        // create the text for the image
        String capText = this.captchaProducer.createText();
        CookieManager cookieManager = new CookieManager(request, response);
        String key = cookieManager.getCookieValue(Constants.USER_SESSION_KEY);
        String sessionKeyDateValue = session.getId();
        if (StringUtils.isBlank(key)) {
            cookieManager.setCookie(Constants.USER_SESSION_KEY,
                    sessionKeyDateValue, SysConfigProperty.getProperty(Constants.DOMAIN_NAME), 3 * 60 * 60 * 24);
            key = sessionKeyDateValue;
        }

        redisClient.set(Constants.USER_SESSION_KEY + "_" + key, capText, -1);

        // create the image with the text
        BufferedImage bi = this.captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
