package com.abc.mapper;

import com.abc.domain.Permission;
import com.abc.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    Role selectByPrimaryKey(Long rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    void insertRolePermissionRel(@Param("rid") Long rid, @Param("pid") Long pid);

    void deleteRolePermissionRel(Long rid);

    List<Long> selectRidByEid(Long id);
}