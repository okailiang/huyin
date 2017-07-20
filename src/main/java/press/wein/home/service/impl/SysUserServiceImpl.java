package press.wein.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.SysRoleUserMapper;
import press.wein.home.dao.SysUserMapper;
import press.wein.home.enumerate.Enums;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.SysRole;
import press.wein.home.model.SysRoleUser;
import press.wein.home.model.SysUser;
import press.wein.home.model.vo.RoleVo;
import press.wein.home.model.vo.SysUserVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.RoleService;
import press.wein.home.service.SysUserService;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.MD5Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author oukailiang
 * @create 2017-07-05 下午6:32
 */
@Service(value = "sysUserService")
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 保存用户信息
     *
     * @param sysUserVo
     * @return
     */
    @Override
    public int saveSysUser(SysUserVo sysUserVo) throws ServiceException {
        //check param
        checkParamNull(sysUserVo.getRoleId(), sysUserVo.getEmail(), sysUserVo.getPhoneNo(), sysUserVo.getUserName(), sysUserVo.getPassword());

        SysUser sysUser = new SysUser();
        BeanUtil.beanCopier(sysUserVo, sysUser);
        //检验重名
        this.checkRepeat(sysUser);
        sysUser.setRole(Enums.UserRole.PRINTER.getValue().byteValue());
        sysUser.setPassword(MD5Util.md5Hex(sysUser.getPassword()));
        sysUserMapper.insertSelective(sysUser);

        //保存用户和角色对应关系
        SysRoleUser roleUser = new SysRoleUser();
        BeanUtil.beanCopier(sysUser, roleUser);
        roleUser.setRoleId(sysUserVo.getRoleId());
        roleUser.setUserId(sysUser.getId());
        roleUser.setId(null);
        return sysRoleUserMapper.insertSelective(roleUser);
    }

    /**
     * 更新用户信息
     *
     * @param sysUserVo
     * @return
     */
    @Override
    public int updateSysUser(SysUserVo sysUserVo) throws ServiceException {
        //check param
        checkParamNull(sysUserVo.getId(), sysUserVo.getRoleId(), sysUserVo.getEmail(), sysUserVo.getPhoneNo(), sysUserVo.getUserName());
        sysUserVo.setPassword(null);

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysUserVo.getId());
        if (sysUser == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NOT_EXIST);
        }
        BeanUtil.beanCopier(sysUserVo, sysUser);

        //检验重名
        this.checkRepeat(sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);


        //保存用户和角色对应关系
        SysRoleUser roleUser = sysRoleUserMapper.getRoleUserByUserId(sysUser.getId());
        if (roleUser == null) {
            //保存用户和角色对应关系
            roleUser = new SysRoleUser();
            BeanUtil.beanCopier(sysUser, roleUser);
            roleUser.setRoleId(sysUserVo.getRoleId());
            roleUser.setUserId(sysUser.getId());
            roleUser.setId(null);
            return sysRoleUserMapper.insertSelective(roleUser);
        }
        roleUser.setRoleId(sysUserVo.getRoleId());
        roleUser.setModifier(sysUserVo.getModifier());
        roleUser.setModifierId(sysUserVo.getModifierId());
        roleUser.setModifyTime(null);

        return sysRoleUserMapper.updateByPrimaryKeySelective(roleUser);
    }

    /**
     * 禁用用户
     *
     * @param sysUserVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int enableSysUser(SysUserVo sysUserVo) throws ServiceException {
        //check param
        checkParamNull(sysUserVo.getId(), sysUserVo.getStatus());
        if (Enums.Status.ALLOW.getValue() != sysUserVo.getStatus().intValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        SysUser user = new SysUser();
        BeanUtil.beanCopier(sysUserVo, user);
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 禁用用户
     *
     * @param sysUserVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int disableSysUser(SysUserVo sysUserVo) throws ServiceException {
        //check param
        checkParamNull(sysUserVo.getId(), sysUserVo.getStatus());
        if (Enums.Status.DENY.getValue() != sysUserVo.getStatus().intValue()) {
            throw ExceptionUtil.createServiceException(ExceptionCode.INVALID_STATUS_VALUE);
        }
        SysUser user = new SysUser();
        BeanUtil.beanCopier(sysUserVo, user);
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 重置密码
     *
     * @param sysUserVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int resetPassword(SysUserVo sysUserVo) throws ServiceException {
        //check param
        checkParamNull(sysUserVo.getId(), sysUserVo.getPassword());

        SysUser user = new SysUser();
        BeanUtil.beanCopier(sysUserVo, user);
        user.setPassword(MD5Util.md5Hex(sysUserVo.getPassword()));
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据用户id逻辑查询
     *
     * @param sysUserVo
     * @return
     */
    @Override
    public int removeSysUser(SysUserVo sysUserVo) throws ServiceException {
        this.checkParamNull(sysUserVo, sysUserVo.getId());
        SysUser sysUser = new SysUser();
        BeanUtil.beanCopier(sysUserVo, sysUser);
        sysUser.setIsDeleted(Constants.IS_DELETED);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        SysRoleUser roleUser = sysRoleUserMapper.getRoleUserByUserId(sysUserVo.getId());
        if (roleUser == null) {
            return 1;
        }
        return sysRoleUserMapper.removeRoleUser(roleUser.getId());
    }

    /**
     * 根据名称查询用户
     *
     * @param user
     * @return
     */
    @Override
    public SysUser getSysUserBySysUserName(SysUser user) throws ServiceException {
        return null;
    }

    /**
     * 获得SysUser
     *
     * @param id
     * @return
     */
    @Override
    public SysUserVo getSysUser(Long id) throws ServiceException {
        checkParamNull(id);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser == null) {
            return null;
        }
        SysUserVo sysUserVo = new SysUserVo();
        BeanUtil.beanCopier(sysUser, sysUserVo);
        //
        SysRoleUser roleUser = sysRoleUserMapper.getRoleUserByUserId(id);
        sysUserVo.setRoleId(roleUser.getRoleId());
        sysUserVo.setRoleName(this.getRoleName(roleUser.getRoleId()));
        return sysUserVo;
    }

    /**
     * 根据查询条件分页查询
     *
     * @param sysUserVo
     * @return
     */
    @Override
    public Page<SysUserVo> listSysUsersWithPage(Page<SysUserVo> page, SysUserVo sysUserVo) throws ServiceException {
        //查询条件
        Map<String, Object> searchParam = CollectionUtil.objectToMap(sysUserVo);
        //分页参数
        setPageParam(searchParam, page);

        List<SysUserVo> sysUserVoList = new ArrayList<>();
        long totalCount = sysUserMapper.countSysUsers(searchParam);
        if (totalCount <= 0) {
            return new Page<>(0, sysUserVoList);
        }
        List<SysUser> sysUserList = sysUserMapper.listSysUsersByPage(searchParam);
        if (CollectionUtil.isNotEmpty(sysUserList)) {
            sysUserVoList = CollectionUtil.copyToDescList(sysUserList, SysUserVo.class);
        }
        //填充角色
        for (SysUserVo userVo : sysUserVoList) {
            SysRoleUser roleUser = sysRoleUserMapper.getRoleUserByUserId(userVo.getId());
            if (roleUser == null) {
                continue;
            }
            userVo.setRoleId(roleUser.getRoleId());
            userVo.setRoleName(this.getRoleName(roleUser.getRoleId()));

        }
        return new Page<>(totalCount, sysUserVoList);
    }

    /**
     * 根据查询条件查询
     *
     * @param sysUserVo
     * @return
     */
    @Override
    public List<SysUser> listSysUsers(SysUserVo sysUserVo) throws ServiceException {
        if (sysUserVo == null) {
            return new ArrayList<>();
        }
        SysUser sysUser = new SysUser();
        BeanUtil.beanCopier(sysUserVo, sysUser);

        return sysUserMapper.listSysUsers(sysUser);
    }

    /**
     * 根据用户id列表查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysUser> listSysUsersByIds(List<Long> ids) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(ids)) {
            return new ArrayList<>();
        }
        return sysUserMapper.listSysUsersByIds(ids);
    }

    private void checkRepeat(SysUser user) throws ServiceException {
        SysUser newUser = new SysUser();
        newUser.setId(user.getId());
        newUser.setUserName(user.getUserName());
        if (sysUserMapper.checkRepeatName(newUser) > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NAME_EXISTED);
        }
        newUser.setUserName(null);
        newUser.setPhoneNo(user.getPhoneNo());
        if (sysUserMapper.checkRepeatName(newUser) > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PHONE_EXISTED);
        }
        newUser.setPhoneNo(null);
        newUser.setEmail(user.getEmail());
        if (sysUserMapper.checkRepeatName(newUser) > 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.EMAIL_EXISTED);
        }
    }

    private String getRoleName(Long roleId) throws ServiceException {
        RoleVo sysRoleVo = roleService.getRoleByRoleId(roleId.intValue());
        return sysRoleVo == null ? null : sysRoleVo.getRoleName();
    }

    private Long getRoleId(Long userId) throws ServiceException {
        SysRoleUser roleUser = sysRoleUserMapper.getRoleUserByUserId(userId);
        return roleUser == null ? null : roleUser.getRoleId();
    }
}
