package com.abc.web.filter;

import com.abc.domain.AjaxRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyFormFilter extends FormAuthenticationFilter
{
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception
    {
        System.out.println("login success----");
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("login success!");
        String jsonString = new ObjectMapper().writeValueAsString(ajaxRes);
        response.getWriter().println(jsonString);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response)
    {
        System.out.println("login failure----");
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(false);
        if(e != null) {
            String name = e.getClass().getName();
            if(name.equals(UnknownAccountException.class.getName())) {
                ajaxRes.setMsg("账号不存在");
            } else if (name.equals(IncorrectCredentialsException.class.getName())) {
                ajaxRes.setMsg("密码错误");
            } else {
                ajaxRes.setMsg("未知错误");
            }
        }

        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(ajaxRes);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }

        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonString);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return false;
    }
}
