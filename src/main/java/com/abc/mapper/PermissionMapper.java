package com.abc.mapper;

import com.abc.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectPermissions(@Param("rid") Long rid);
}