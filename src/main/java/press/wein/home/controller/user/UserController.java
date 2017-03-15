package press.wein.home.controller.user;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.model.User;

import java.util.Date;

/**
 * 用户管理
 *
 * @author oukailiang
 * @create 2017-02-20 下午6:42
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(User user, Model model) {

        System.out.println("1Hello world");
        user.setUserName("小明1");
        user.setCreateTime(new Date());
        user.setCreator("小红32");
        user.setEmail("123@163.com");
        LOG.info(JSON.toJSONString(user));
        return user;
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String login(User user, Model model) {

        System.out.println("1Hello world");
        user.setUserName("小明1");
        user.setCreateTime(new Date());
        user.setCreator("小红32");
        user.setEmail("123@163.com");
        LOG.info(JSON.toJSONString(user));
        return "/login";
    }

}
