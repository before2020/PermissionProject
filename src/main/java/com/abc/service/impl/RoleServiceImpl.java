package com.abc.service.impl;

import com.abc.domain.PageResult;
import com.abc.domain.Permission;
import com.abc.domain.QueryPage;
import com.abc.domain.Role;
import com.abc.mapper.RoleMapper;
import com.abc.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult getRoleList(QueryPage qp)
    {
        Page<Object> page = PageHelper.startPage(qp.getPage(), qp.getRows());
        List<Role> roleList = roleMapper.selectAll();

        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(roleList);
        return pageResult;
    }

    @Override
    public void saveRole(Role role)
    {
        roleMapper.insert(role);
        //保存角色和权限的关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRolePermissionRel(role.getRid(), permission.getPid());
        }
    }

    @Override
    public void updateRole(Role role)
    {
        roleMapper.updateByPrimaryKey(role);
        roleMapper.deleteRolePermissionRel(role.getRid());
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRolePermissionRel(role.getRid(), permission.getPid());
        }
    }

    @Override
    public void deleteRole(Long rid)
    {
        roleMapper.deleteByPrimaryKey(rid);
        roleMapper.deleteRolePermissionRel(rid);
    }

    @Override
    public List<Role> getRoleListNoPage()
    {
        return roleMapper.selectAll();
    }

    @Override
    public List<Long> getRolesByEid(Long id)
    {
        return roleMapper.selectRidByEid(id);
    }

}
