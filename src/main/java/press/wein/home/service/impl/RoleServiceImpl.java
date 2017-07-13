package press.wein.home.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import press.wein.home.common.Page;
import press.wein.home.constant.Constants;
import press.wein.home.dao.SysRoleMapper;
import press.wein.home.dao.SysRoleMenuMapper;
import press.wein.home.dao.SysRoleUserMapper;
import press.wein.home.exception.ExceptionCode;
import press.wein.home.exception.ExceptionUtil;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.Menu;
import press.wein.home.model.SysRole;
import press.wein.home.model.SysRoleMenu;
import press.wein.home.model.SysRoleUser;
import press.wein.home.model.vo.RoleVo;
import press.wein.home.service.BaseService;
import press.wein.home.service.MenuService;
import press.wein.home.service.RoleService;
import press.wein.home.util.BeanUtil;
import press.wein.home.util.CollectionUtil;
import press.wein.home.util.CommonUtil;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private MenuService menuService;

    /**
     * 保存角色
     *
     * @param roleVo
     * @return
     * @throws ServiceException
     */
    @Override
    public int saveRole(RoleVo roleVo) throws ServiceException {
        //check param
        checkParamNull(roleVo.getRoleName(), roleVo.getDataLevel(), roleVo.getAssignAuthority(), roleVo.getSmsVerify());
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
        //check param
        checkParamNull(roleVo.getRoleName(), roleVo.getDataLevel(), roleVo.getAssignAuthority(), roleVo.getSmsVerify());

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

        SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
        if (role == null) {
            return null;
        }
        RoleVo roleVo = new RoleVo();
        BeanUtil.beanCopier(role, roleVo);

        return roleVo;
    }

    /**
     *  通过用户id获得角色
     *
     * @param userId
     * @return
     * @throws ServiceException
     */
    @Override
    public RoleVo getRoleByUserId(Long userId) throws ServiceException {
        checkParamNull(userId);

        SysRoleUser sysRoleUser = sysRoleUserMapper.getRoleUserByUserId(userId);
        if (sysRoleUser == null) {
            return null;
        }
        SysRole role = sysRoleMapper.selectByPrimaryKey(sysRoleUser.getRoleId().intValue());
        if (role == null) {
            return null;
        }
        RoleVo roleVo = new RoleVo();
        BeanUtil.beanCopier(role, roleVo);
        return roleVo;
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
        //查询条件
        Map<String, Object> searchParam = CollectionUtil.objectToMap(record);
        //分页参数
        setPageParam(searchParam, page);

        List<RoleVo> roleVoList = new ArrayList<>();
        long totalCount = sysRoleMapper.countRoles(searchParam);
        if (totalCount <= 0) {
            return new Page<>(0, roleVoList);
        }
        List<SysRole> roleList = sysRoleMapper.listRolesByPage(searchParam);
        if (CollectionUtil.isNotEmpty(roleList)) {
            this.copyToRoleVoList(roleList, roleVoList);
        }
        return new Page<>(totalCount, roleVoList);
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
        SysRole role = new SysRole();
        BeanUtil.beanCopier(roleVo, role);

        List<SysRole> roleList = sysRoleMapper.listRoles(role);

        List<RoleVo> roleVoList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleList)) {
            this.copyToRoleVoList(roleList, roleVoList);
        }
        return roleVoList;
    }

    /**
     * 根据id列表查询列表
     *
     * @param roleIds
     * @return
     * @throws ServiceException
     */
    @Override
    public List<RoleVo> listRolesByIds(List<Integer> roleIds) throws ServiceException {
        if (CollectionUtil.isNullOrEmpty(roleIds)) {
            return new ArrayList<>();
        }
        List<SysRole> roleList = sysRoleMapper.listRolesByIds(roleIds);

        List<RoleVo> roleVoList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleList)) {
            this.copyToRoleVoList(roleList, roleVoList);
        }
        return roleVoList;
    }

    /**
     * 根据id逻辑删除
     *
     * @param roleId
     * @return
     */
    @Override
    public int removeRole(Integer roleId) throws ServiceException {
        if (roleId == null) {
            throw ExceptionUtil.createServiceException(ExceptionCode.PARAM_NULL);
        }
        List<SysRoleUser> roleUsers = sysRoleUserMapper.listRoleUsersByRoleIds(Arrays.asList(roleId.longValue()));
        if (CollectionUtil.isNotEmpty(roleUsers)) {
            throw ExceptionUtil.createServiceException(ExceptionCode.NO_REMOVE_ROLE);
        }
        sysRoleMapper.deleteRole(roleId);

        //删除角色菜单关联
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.listRoleMenusByRoleIds(Arrays.asList(roleId));
        if (CollectionUtil.isNullOrEmpty(roleMenuList)) {
            return 1;
        }
        for (SysRoleMenu roleMenu : roleMenuList) {
            sysRoleMenuMapper.removeMenuRole(roleMenu.getId());
        }
        return roleMenuList.size();
    }

    /**
     * 给角色添加菜单权限
     *
     * @param roleVo
     * @return
     */
    @Override
    public int saveMenuPermission(RoleVo roleVo) throws ServiceException {
        //check param
        this.checkParamNull(roleVo, roleVo.getId(), roleVo.getMenuIds());

        List<Integer> menuIds = roleVo.getMenuIds();
        for (Integer menuId : menuIds) {
            SysRoleMenu roleMenu = sysRoleMenuMapper.getRoleMenuByRoleIdAndMenuId(roleVo.getId(), menuId);
            if (roleMenu != null) {
                continue;
            }
            roleMenu = new SysRoleMenu();
            BeanUtil.beanCopier(roleVo, roleMenu);
            roleMenu.setId(null);
            roleMenu.setRoleId(roleVo.getId());
            roleMenu.setMenuId(menuId);
            sysRoleMenuMapper.insertSelective(roleMenu);
        }
        return menuIds.size();
    }

    /**
     * 取消角色菜单权限
     *
     * @param roleVo
     * @return
     */
    @Override
    public int removeMenuPermission(RoleVo roleVo) throws ServiceException {
        //check param
        this.checkParamNull(roleVo, roleVo.getId(), roleVo.getMenuIds());

        List<Integer> menuIds = roleVo.getMenuIds();
        for (Integer menuId : menuIds) {
            SysRoleMenu roleMenu = sysRoleMenuMapper.getRoleMenuByRoleIdAndMenuId(roleVo.getId(), menuId);
            if (roleMenu == null) {
                continue;
            }
            roleMenu.setIsDeleted(Constants.IS_DELETED);
            roleMenu.setModifierId(roleVo.getModifierId());
            roleMenu.setModifier(roleVo.getModifier());
            roleMenu.setModifyTime(new Date());
            sysRoleMenuMapper.updateByPrimaryKeySelective(roleMenu);
        }
        return menuIds.size();
    }

    /**
     * 通过roleId获得role对应的菜单关系
     *
     * @param roleId
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<Integer, List<SysRoleMenu>> getRoleIdToMenusMap(Integer roleId) throws ServiceException {
        //check param
        this.checkParamNull(roleId);

        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.listRoleMenusByRoleIds(Arrays.asList(roleId));
        if (CollectionUtil.isNullOrEmpty(roleMenuList)) {
            return new HashMap<>();
        }
        return roleMenuList.stream().collect(Collectors.groupingBy(SysRoleMenu::getRoleId));
    }

    private void copyToRoleVoList(List<SysRole> roleList, List<RoleVo> roleVoList) {
        for (SysRole role : roleList) {
            RoleVo roleVo = new RoleVo();
            BeanUtil.beanCopier(role, roleVo);
            roleVoList.add(roleVo);
        }
    }
}
