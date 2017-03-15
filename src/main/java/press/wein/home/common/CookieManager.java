package press.wein.home.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import press.wein.home.constant.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookie管理类
 *
 * @author oukailiang
 * @create 2017-02-22 下午5:29
 */

public class CookieManager {

    private static final Logger LOG = LoggerFactory.getLogger(CookieManager.class);

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieManager(HttpServletRequest req, HttpServletResponse res) {
        request = req;
        response = res;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 获取远程IP地址
     * 获取客户端真实IP(代理服务器会做代理)
     *
     * @return
     */
    public String getRemoteIp() {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 设置cookie
     *
     * @param name
     * @param value
     * @param domain
     * @param expire
     */
    public void setCookie(String name, String value, String domain, int expire) {
        try {
            if (StringUtils.isNotEmpty(value)) {
                value = URLEncoder.encode(value, Constants.ENCODING);
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error("CookieManager.setCookie : no support encoding", e);
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        if (expire >= 0) {
            cookie.setMaxAge(expire);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取某个cookie值
     *
     * @param name
     * @return
     */
    public String getCookieValue(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        Cookie cookie;
        try {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equalsIgnoreCase(name) && StringUtils.isNotEmpty(cookie.getValue())) {
                    return URLDecoder.decode(cookie.getValue(), Constants.ENCODING);
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error("CookieManager.getCookieValue:no support encoding", e);
        }
        return null;
    }

    public void deleteCookie(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return;

        Cookie cookie;
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if (cookie.getName().equalsIgnoreCase(name)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

    }
}
