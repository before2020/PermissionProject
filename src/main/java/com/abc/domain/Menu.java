package com.abc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Menu {
    private Long id;

    private String url;

    private String text;

    private Menu parent;

}