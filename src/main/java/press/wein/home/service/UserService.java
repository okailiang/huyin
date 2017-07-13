package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.User;
import press.wein.home.model.vo.UserVo;

import java.util.List;

/**
 * 用户service接口层
 *
 * @author oukailiang
 * @create 2017-02-28 下午9:10
 */

public interface UserService {


    /**
     * 保存用户信息
     *
     * @param userVo
     * @return
     */
    int saveUser(UserVo userVo) throws ServiceException;

    /**
     * 更新用户信息
     *
     * @param userVo
     * @return
     */
    int updateUser(UserVo userVo) throws ServiceException;

    /**
     * 禁用用户
     *
     * @param userVo
     * @return
     * @throws ServiceException
     */
    int enableUser(UserVo userVo) throws ServiceException;

    /**
     * 禁用用户
     *
     * @param userVo
     * @return
     * @throws ServiceException
     */
    int disableUser(UserVo userVo) throws ServiceException;

    /**
     * 重置密码
     *
     * @param userVo
     * @return
     * @throws ServiceException
     */
    int resetPassword(UserVo userVo) throws ServiceException;

    /**
     * 根据用户id逻辑查询
     *
     * @param id
     * @return
     */
    String removeUserById(long id) throws ServiceException;

    /**
     * 根据wxOpenid逻辑删除
     *
     * @param wxOpenid
     * @return
     */
    String removeUserByWxOpenid(String wxOpenid) throws ServiceException;

    /**
     * 根据名称查询用户
     *
     * @param user
     * @return
     */
    User getUserByUserName(User user) throws ServiceException;

    /**
     * 根据wxOpenid查询用户
     *
     * @param wxOpenid
     * @return
     */
    User getUserByWxOpenid(String wxOpenid) throws ServiceException;

    /**
     * 根据条件查询用户
     *
     * @param userVo
     * @return
     */
    User getUser(UserVo userVo) throws ServiceException;

    /**
     * 根据查询条件分页查询
     *
     * @param userVo
     * @return
     */
    Page<UserVo> listUsersWithPage(Page<UserVo> page, UserVo userVo) throws ServiceException;

    /**
     * 根据查询条件查询
     *
     * @param userVo
     * @return
     */
    List<User> listUsers(UserVo userVo) throws ServiceException;

    /**
     * 根据用户id列表查询
     *
     * @param ids
     * @return
     */
    List<User> listUsersByIds(List<Long> ids) throws ServiceException;
}
