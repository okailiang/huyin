package press.wein.home.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.CookieManager;
import press.wein.home.common.SysConfigProperty;
import press.wein.home.constant.Constants;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.User;
import press.wein.home.model.vo.UserLoginVo;
import press.wein.home.model.vo.UserVo;
import press.wein.home.redis.RedisClient;
import press.wein.home.service.LoginService;
import press.wein.home.service.UserService;
import press.wein.home.util.CommonUtil;
import press.wein.home.util.EmailVerifyUtil;
import press.wein.home.util.ResponseUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用户登录controller
 *
 * @author oukailiang
 * @create 2017-02-22 下午4:15
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private Producer captchaProducer;

    /**
     * 邮箱注册用户发送验证码
     *
     * @param request
     * @param email
     * @return
     */
    @RequestMapping(value = "/send/emailCode", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity sendEmailRegisterCode(HttpServletRequest request, @RequestParam(value = "email") String email) throws ServiceException {
        LOG.info("sendEmailRegisterCode email : " + email);
        if (email == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PARAM_NULL);
        }
        if (!CommonUtil.isMatchEmail(email)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_ERROR);
        }
        User user = new User();
        user.setEmail(email);
        if (userService.getUserByUserName(user) != null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_EXIST);
        }

        try {
            String code = CommonUtil.getSixRandom();
            redisClient.set(Constants.REGISTER_EMAIL_CODE + email, code, 5);
            EmailVerifyUtil.verifyEmail(email, code);
        } catch (Exception e) {
            ResponseUtils.error("发送注册邮箱验证码失败！");
            LOG.error("sendEmailRegisterCode error!", e.getMessage());
        }

        return ResponseUtils.success("注册邮箱验证码发送成功");
    }

    /**
     * 邮箱注册用户发送验证码
     *
     * @param request
     * @param userLoginVo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity register(HttpServletRequest request, @RequestBody UserLoginVo userLoginVo) throws ServiceException {
        loginService.register(userLoginVo);
        return ResponseUtils.success("注册成功，请登录");
    }

    /**
     *  重置密码发送验证码验证
     *
     * @param request
     * @param email
     * @return
     */
    @RequestMapping(value = "/resetPassword/code", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity resetPasswordCode(HttpServletRequest request, @RequestParam(value = "email") String email) throws ServiceException {
        LOG.info("resetPasswordCode email : " + email);
        if (email == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PARAM_NULL);
        }
        if (!CommonUtil.isMatchEmail(email)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_ERROR);
        }
        User user = new User();
        user.setEmail(email);
        user = userService.getUserByUserName(user);
        if (user == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_NOT_EXIST);
        }

        try {
            String code = CommonUtil.getSixRandom();
            redisClient.set(Constants.REGISTER_EMAIL_CODE + email, code, 5);
            EmailVerifyUtil.verifyEmail(email, code);
        } catch (Exception e) {
            ResponseUtils.error("发送验证码失败！");
            LOG.error("resetPasswordCode error!", e.getMessage());
        }
        return ResponseUtils.success("验证码发送成功");
    }

    /**
     *  重置密码发送验证码验证
     *
     * @param request
     * @param email
     * @return
     */
    @RequestMapping(value = "/resetPassword/checkCode", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity resetPasswordCheckCode(HttpServletRequest request, @RequestParam(value = "email") String email
            , @RequestParam(value = "code") String code) throws ServiceException {
        LOG.info("resetPasswordCheckCode email : " + email);
        if (email == null || code == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PARAM_NULL);
        }
        if (!CommonUtil.isMatchEmail(email)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_ERROR);
        }
        User user = new User();
        user.setEmail(email);
        user = userService.getUserByUserName(user);
        if (user == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_NOT_EXIST);
        }

        try {
            String resetCode = redisClient.get(Constants.REGISTER_EMAIL_CODE + email);
            if (resetCode == null || !resetCode.equals(code)) {
                throw ExceptionUtil.createServiceException(ExceptionCode.KAPTCHA_CODE_ERROR);
            }
        } catch (Exception e) {
            ResponseUtils.error("重置密码验证码验证失败！");
            LOG.error("resetPasswordCheckCode error!", e.getMessage());
        }
        Map resultMap = new HashMap<>();
        resultMap.put("id", user.getId());
        resultMap.put("email", user.getEmail());
        resultMap.put("message", "验证码校验成功");
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setPassword(user.getPassword());
        resultMap.put("user", userVo);
        return ResponseUtils.success(resultMap);
    }

    /**
     * 重置密码
     *
     * @param request
     * @param userLoginVo
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity resetPassword(HttpServletRequest request, @RequestBody UserLoginVo userLoginVo) throws ServiceException {
        loginService.resetPassword(userLoginVo);
        return ResponseUtils.success("密码修改成功，请重新登录");
    }


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
    public String login(@RequestBody UserLoginVo userLoginVo, HttpServletRequest request, HttpServletResponse response) {
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
