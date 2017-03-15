package press.wein.home.service;

import press.wein.home.common.Paper;
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
    String saveUser(UserVo userVo);

    /**
     * 更新用户信息
     *
     * @param userVo
     * @return
     */
    String updateUser(UserVo userVo);

    /**
     * 根据用户id逻辑查询
     *
     * @param id
     * @return
     */
    String removeUserById(long id);

    /**
     * 根据wxOpenid逻辑删除
     *
     * @param wxOpenid
     * @return
     */
    String removeUserByWxOpenid(String wxOpenid);

    /**
     * 根据名称查询用户
     *
     * @param user
     * @return
     */
    User getUserByUserName(User user);

    /**
     * 根据wxOpenid查询用户
     *
     * @param wxOpenid
     * @return
     */
    User getUserByWxOpenid(String wxOpenid);

    /**
     * 根据条件查询用户
     *
     * @param userVo
     * @return
     */
    User getUser(UserVo userVo);

    /**
     * 根据查询条件分页查询
     *
     * @param userVo
     * @return
     */
    Paper<UserVo> listUsersWithPage(Paper<UserVo> paper, UserVo userVo);

    /**
     * 根据查询条件查询
     *
     * @param userVo
     * @return
     */
    List<User> listUsers(UserVo userVo);

    /**
     * 根据用户id列表查询
     *
     * @param ids
     * @return
     */
    List<User> listUsersByIds(List<Long> ids);
}
