package com.abc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController
{
    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }
}
