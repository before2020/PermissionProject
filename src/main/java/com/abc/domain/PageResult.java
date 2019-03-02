package com.abc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter@ToString
public class PageResult
{
    private Long total;
    private List rows = new ArrayList<>();
}
