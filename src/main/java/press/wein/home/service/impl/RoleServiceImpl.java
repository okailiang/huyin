package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.dao.SysRoleMapper;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.SysRole;
import press.wein.home.model.vo.RoleVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.RoleService;
import press.wein.home.util.BeanUtil;

import java.util.List;

/**
 * 角色实现类
 *
 * @author oukailiang
 * @create 2017-05-16 上午12:34
 */
@Service(value = "roleService")
public class RoleServiceImpl extends BaseService implements RoleService {
    public static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 保存角色
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int saveRole(RoleVo roleVo) throws ServiceException {
        SysRole role = new SysRole();
        BeanUtil.beanCopier(roleVo, role);
        return sysRoleMapper.insertSelective(role);
    }

    /**
     * 更新角色信息
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int updateRole(RoleVo roleVo) throws ServiceException {
        SysRole role = new SysRole();
        BeanUtil.beanCopier(roleVo, role);
        return sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 根据ID查询角色
     *
     * @param roleId
     * @return
     * @throws ServiceException
     */
    @Override
    public RoleVo getRoleByRoleId(Integer roleId) throws ServiceException {

        checkParamNull(roleId);
        if (roleId == null || roleId <= 0) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PARAM_NULL);
        }
        //SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);

        return null;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param record
     * @return
     * @throws ServiceException
     */
    @Override
    public Page<RoleVo> listRolesByPage(Page<RoleVo> page, RoleVo record) throws ServiceException {
        return null;
    }

    /**
     * 根据条件查询列表
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    @Override
    public List<RoleVo> listRoles(RoleVo roleVo) throws ServiceException {
        return null;
    }

    /**
     * 根据id列表查询列表
     *
     * @param roleIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<RoleVo> listRolesByRoleIds(List<Integer> roleIds) throws ServiceException {
        return null;
    }

    /**
     * 根据id逻辑删除
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteRole(Integer roleId) {
        return 0;
    }
}
