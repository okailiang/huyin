package press.wein.home.dao;

import press.wein.home.model.User;
import press.wein.home.model.vo.UserVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据名称查询用户
     * @param user
     * @return
     */
    User getUserByUserName(User user);

    /**
     * 根据wxOpenid查询用户
     * @param wxOpenid
     * @return
     */
    User getUserByWxOpenid(String wxOpenid);

    /**
     * 根据条件查询用户
     * @param userVo
     * @return
     */
    User getUser(UserVo userVo);

    /**
     * 根据查询条件查询总数
     * @param userVo
     * @return
     */
    long countTotalUsers(UserVo userVo);

    /**
     * 根据查询条件分页查询
     * @param userVo
     * @return
     */
    List<User> listUsersWithPage(UserVo userVo);

    /**
     * 根据查询条件查询
     * @param userVo
     * @return
     */
    List<User> listUsers(UserVo userVo);

    /**
     * 根据用户id列表查询
     * @param ids
     * @return
     */
    List<User> listUsersByIds(List<Long> ids);

    /**
     * 根据用户id逻辑查询
     * @param id
     * @return
     */
    int removeUserById(long id);

    /**
     * 根据wxOpenid逻辑删除
     * @param wxOpenid
     * @return
     */
    int removeUserByWxOpenid(String wxOpenid);
}