package com.abc.service;

import com.abc.domain.PageResult;
import com.abc.domain.Permission;
import com.abc.domain.QueryPage;
import com.abc.domain.Role;

import java.util.List;

public interface RoleService
{
    PageResult getRoleList(QueryPage qp);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long rid);

    List<Role> getRoleListNoPage();

    List<Long> getRolesByEid(Long id);
}
