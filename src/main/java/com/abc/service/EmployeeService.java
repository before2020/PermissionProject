package com.abc.service;

import com.abc.domain.Employee;
import com.abc.domain.PageResult;
import com.abc.domain.QueryPage;
import com.abc.domain.Role;

import java.util.List;

public interface EmployeeService
{
    PageResult getEmployeeList(QueryPage qp);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void updateState(Long id);

    Employee getEmployeeByUsername(String username);

    List<String> getRolesByEid(Long id);

    List<String> getPermissionsByEid(Long id);

}
