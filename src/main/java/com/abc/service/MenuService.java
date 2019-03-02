package com.abc.service;

import com.abc.domain.PageResult;
import com.abc.domain.QueryPage;

public interface MenuService
{

    PageResult getMenuList(QueryPage qp);
}
