package com.abc.web;

import com.abc.domain.AjaxRes;
import com.abc.domain.PageResult;
import com.abc.domain.QueryPage;
import com.abc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping("/getMenuList")
    @ResponseBody
    public PageResult getMenuList(QueryPage qp) {
        return menuService.getMenuList(qp);
    }
}
