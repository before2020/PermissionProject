package com.abc.service.impl;

import com.abc.domain.Department;
import com.abc.mapper.DepartmentMapper;
import com.abc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentList()
    {
        return departmentMapper.selectAll();
    }
}
