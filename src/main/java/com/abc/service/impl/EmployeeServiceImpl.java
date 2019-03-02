package com.abc.service.impl;

import com.abc.domain.Employee;
import com.abc.domain.PageResult;
import com.abc.domain.QueryPage;
import com.abc.domain.Role;
import com.abc.mapper.EmployeeMapper;
import com.abc.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageResult getEmployeeList(QueryPage qp)
    {
        Page<Object> page = PageHelper.startPage(qp.getPage(), qp.getRows());
        List<Employee> employees = employeeMapper.selectAll(qp);
        /*封装成页*/
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        System.out.println(page.getTotal());
        pageResult.setRows(employees);
        return pageResult;
    }

    @Override
    public void saveEmployee(Employee e)
    {
        Md5Hash md5Hash = new Md5Hash(e.getPassword(), e.getUsername(), 3);
        e.setPassword(md5Hash.toString());
        employeeMapper.insert(e);
        for (Role role : e.getRoles()) {
            employeeMapper.insertEmpRoleRel(e.getId(), role.getRid());
        }
    }

    @Override
    public void updateEmployee(Employee employee)
    {
        employeeMapper.updateByPrimaryKey(employee);
        employeeMapper.deleteEmpRoleRel(employee.getId());
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmpRoleRel(employee.getId(), role.getRid());
        }
    }

    @Override
    public void updateState(Long id)
    {
        employeeMapper.updateState(id);
    }

    @Override
    public Employee getEmployeeByUsername(String username)
    {
        return employeeMapper.getEmployeeByUsername(username);
    }

    @Override
    public List<String> getRolesByEid(Long id)
    {
        return employeeMapper.getRolesByEid(id);
    }

    @Override
    public List<String> getPermissionsByEid(Long id)
    {
        return employeeMapper.getPermissionsByEid(id);
    }

}
