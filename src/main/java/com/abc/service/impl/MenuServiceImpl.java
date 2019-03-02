package com.abc.service.impl;

import com.abc.domain.Menu;
import com.abc.domain.PageResult;
import com.abc.domain.QueryPage;
import com.abc.mapper.MenuMapper;
import com.abc.service.MenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageResult getMenuList(QueryPage qp)
    {
        Page<Object> page = PageHelper.startPage(qp.getPage(), qp.getRows());
        List<Menu> menuList = menuMapper.selectAll();

        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(menuList);

        return  pageResult;
    }
}
