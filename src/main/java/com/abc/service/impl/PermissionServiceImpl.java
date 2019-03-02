package com.abc.service.impl;

import com.abc.domain.Permission;
import com.abc.mapper.PermissionMapper;
import com.abc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService
{
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionList()
    {
        return permissionMapper.selectAll();
    }

    @Override
    public List<Permission> getPermissionsByRid(Long rid)
    {
        return permissionMapper.selectPermissions(rid);
    }
}
