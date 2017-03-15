package press.wein.home.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import press.wein.home.base.BaseTest;
import press.wein.home.model.vo.UserVo;
import press.wein.home.service.UserService;
import press.wein.home.util.MD5Util;

import java.util.Date;

/**
 * @author oukailiang
 * @create 2017-02-28 下午11:41
 */

public class UserTest extends BaseTest {
    @Autowired
    private UserService userService;


    @Test
    public void testSaveUser() {
        UserVo userVo = new UserVo();

        userVo.setCreator("okl");
        userVo.setCreateTime(new Date());
        userVo.setUserName("oukailiang1");
        userVo.setEmail("okl@123.com");
        userVo.setEmailVerify((byte) 0);
        userVo.setErrorTimes(1);
        userVo.setPassword(MD5Util.md5Hex("1234567"));
        userVo.setPhoneNo("1537003****");
        userVo.setPhoneVertify((byte) 0);
        userVo.setCreator("okl");
        userVo.setRole((byte) 1);
        userVo.setStatus((byte) 0);
        userVo.setIsDeleted((byte) 0);
        userVo.setModifier("okl");
        userVo.setModifierId(1l);
        userVo.setModifyTime(new Date());
        String result = userService.saveUser(userVo);
    }
}
