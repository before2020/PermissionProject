package com.abc.service;

import com.abc.domain.Permission;

import java.util.List;

public interface PermissionService
{
    List<Permission> getPermissionList();

    List<Permission> getPermissionsByRid(Long rid);
}
