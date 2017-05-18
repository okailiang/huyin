package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.UserMapper;
import press.wein.home.model.User;
import press.wein.home.model.vo.UserVo;
import press.wein.home.redis.RedisClient;
import press.wein.home.service.UserService;

import java.util.List;

/**
 * 用户service实现层
 *
 * @author oukailiang
 * @create 2017-02-28 下午9:10
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public String saveUser(UserVo userVo) {

        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        userMapper.insertSelective(user);
        return Constants.SUCCESS;
    }

    @Override
    public String updateUser(UserVo userVo) {
        return null;
    }

    @Override
    public String removeUserById(long id) {
        return null;
    }

    @Override
    public String removeUserByWxOpenid(String wxOpenid) {
        return null;
    }

    @Override
    public User getUserByUserName(User user) {
        return userMapper.getUserByUserName(user);
    }

    @Override
    public User getUserByWxOpenid(String wxOpenid) {
        return null;
    }

    @Override
    public User getUser(UserVo userVo) {
        return null;
    }

    @Override
    public Page<UserVo> listUsersWithPage(Page<UserVo> page, UserVo userVo) {
        return null;
    }

    @Override
    public List<User> listUsers(UserVo userVo) {
        return null;
    }

    @Override
    public List<User> listUsersByIds(List<Long> ids) {
        return null;
    }
}
