package com.abc.web;

import com.abc.domain.Permission;
import com.abc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController
{
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/getPermissionList")
    @ResponseBody
    public List<Permission> getPermissionList() {
        System.out.println("permission controller");
        return permissionService.getPermissionList();
    }

    @RequestMapping("/getPermissionsByRid")
    @ResponseBody
    public List<Permission> getPermissionsByRid(Long rid) {
        List<Permission> list = permissionService.getPermissionsByRid(rid);
        return list;
    }
}
