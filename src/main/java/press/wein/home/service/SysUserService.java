package press.wein.home.service;

import press.wein.home.common.Page;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.SysUser;
import press.wein.home.model.vo.SysUserVo;

import java.util.List;

/**
 * @author oukailiang
 * @create 2017-07-05 下午6:32
 */
public interface SysUserService {

    /**
     * 保存用户信息
     *
     * @param userVo
     * @return
     */
    int saveSysUser(SysUserVo userVo) throws ServiceException;

    /**
     * 更新用户信息
     *
     * @param userVo
     * @return
     */
    int updateSysUser(SysUserVo userVo) throws ServiceException;

    /**
     * 禁用用户
     *
     * @param userVo
     * @return
     * @throws ServiceException
     */
    int enableSysUser(SysUserVo userVo) throws ServiceException;

    /**
     * 禁用用户
     *
     * @param userVo
     * @return
     * @throws ServiceException
     */
    int disableSysUser(SysUserVo userVo) throws ServiceException;

    /**
     * 重置密码
     *
     * @param userVo
     * @return
     * @throws ServiceException
     */
    int resetPassword(SysUserVo userVo) throws ServiceException;

    /**
     * 根据用户id逻辑查询
     *
     * @param userVo
     * @return
     */
    int removeSysUser(SysUserVo userVo) throws ServiceException;

    /**
     * 根据名称查询用户
     *
     * @param user
     * @return
     */
    SysUser getSysUserBySysUserName(SysUser user) throws ServiceException;

    /**
     * 获得SysUser
     *
     * @param id
     * @return
     */
    SysUserVo getSysUser(Long id) throws ServiceException;


    /**
     * 根据查询条件分页查询
     *
     * @param userVo
     * @return
     */
    Page<SysUserVo> listSysUsersWithPage(Page<SysUserVo> page, SysUserVo userVo) throws ServiceException;

    /**
     * 根据查询条件查询
     *
     * @param userVo
     * @return
     */
    List<SysUser> listSysUsers(SysUserVo userVo) throws ServiceException;

    /**
     * 根据用户id列表查询
     *
     * @param ids
     * @return
     */
    List<SysUser> listSysUsersByIds(List<Long> ids) throws ServiceException;
}
