package com.abc.mapper;

import com.abc.domain.Employee;
import com.abc.domain.QueryPage;
import com.abc.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(QueryPage qp);

    int updateByPrimaryKey(Employee record);

    void updateState(Long id);

    void insertEmpRoleRel(@Param("eid") Long id, @Param("rid") Long rid);

    void deleteEmpRoleRel(Long id);

    Employee getEmployeeByUsername(String username);

    List<String> getRolesByEid(Long id);

    List<String> getPermissionsByEid(Long id);
}