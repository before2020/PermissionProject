package com.abc.web;

import com.abc.domain.PageResult;
import com.abc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employeeList")
    @ResponseBody
    public PageResult getEmployeeList() {
       return employeeService.getEmployeeList();
    }

    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }
}
