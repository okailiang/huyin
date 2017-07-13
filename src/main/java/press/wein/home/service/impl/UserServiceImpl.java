package press.wein.home.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.UserMapper;
import press.wein.home.enumerate.Enums;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.SysRole;
import press.wein.home.model.User;
import press.wein.home.model.vo.RoleVo;
import press.wein.home.model.vo.UserVo;
import press.wein.home.redis.RedisClient;
import press.wein.home.service.BaseService;
import press.wein.home.service.UserService;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.MD5Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户service实现层
 *
 * @author oukailiang
 * @create 2017-02-28 下午9:10
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public int saveUser(UserVo userVo) throws ServiceException{

        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(UserVo userVo)throws ServiceException{
        //check param
        checkParamNull(userVo.getId());

        User user = new User();
        BeanUtil.beanCopier(userVo, user);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int enableUser(UserVo userVo) throws ServiceException{
        //check param
        checkParamNull(userVo.getId(), userVo.getStatus());
        if(Enums.Status.ALLOW.getValue() != userVo.getStatus().intValue()){
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        User user = new User();
        user.setId(userVo.getId());
        user.setStatus(userVo.getStatus());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int disableUser(UserVo userVo) throws ServiceException{
        //check param
        checkParamNull(userVo.getId(), userVo.getStatus());
        if(Enums.Status.DENY.getValue() != userVo.getStatus().intValue()){
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        User user = new User();
        user.setId(userVo.getId());
        user.setStatus(userVo.getStatus());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int resetPassword(UserVo userVo) throws ServiceException{
        //check param
        checkParamNull(userVo.getId(), userVo.getPassword());

        User user = new User();
        user.setId(userVo.getId());
        user.setPassword(MD5Util.md5Hex(userVo.getPassword()));
        return userMapper.updateByPrimaryKeySelective(user);
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
    public Page<UserVo> listUsersWithPage(Page<UserVo> page, UserVo userVo) throws ServiceException {
        //查询条件
        Map<String, Object> searchParam = CollectionUtil.objectToMap(userVo);
        //分页参数
        setPageParam(searchParam, page);

        List<UserVo> userVoList = new ArrayList<>();
        long totalCount = userMapper.countTotalUsers(searchParam);
        if (totalCount <= 0) {
            return new Page<>(0, userVoList);
        }
        List<User> userList = userMapper.listUsersWithPage(searchParam);
        if (CollectionUtil.isNotEmpty(userList)) {
            userVoList = CollectionUtil.copyToDescList(userList, UserVo.class);
        }
        return new Page<>(totalCount, userVoList);
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
