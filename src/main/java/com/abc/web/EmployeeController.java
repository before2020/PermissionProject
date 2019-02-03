package com.abc.web;

import com.abc.domain.AjaxRes;
import com.abc.domain.Employee;
import com.abc.domain.PageResult;
import com.abc.domain.QueryPage;
import com.abc.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getEmployeeList")
    @ResponseBody
    public PageResult getEmployeeList(QueryPage qp)
    {
        return employeeService.getEmployeeList(qp);
    }

    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee() {
        return "employee";
    }

    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();

        try{
            employeeService.saveEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("保存成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }

        return ajaxRes;
    }

    @RequestMapping("/updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes updateEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("编辑成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("编辑失败");
        }
        return ajaxRes;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更改成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更改失败");
        }
        return ajaxRes;
    }

    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws IOException
    {
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if(methodAnnotation != null) {
            //Ajax请求,不能重定向，必须返回json字符串
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("您没有权限操作");
            String s = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(s);

        } else {
            response.sendRedirect("nopermission.jsp");
        }
    }
}
