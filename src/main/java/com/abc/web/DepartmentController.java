package com.abc.web;

import com.abc.domain.Department;
import com.abc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController
{
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/getDepartmentList")
    @ResponseBody
    public List<Department> getDepartmentList() {
        return departmentService.getDepartmentList();
    }
}
