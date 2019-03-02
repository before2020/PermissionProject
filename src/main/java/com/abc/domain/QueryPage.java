package com.abc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class QueryPage
{
    private int page;
    private int rows;
    private String keyword;
}
