package com.andy.project1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    private Integer category_id;
    private String name;
    private String picture;

    public final static int CATEGORY_NAME_MAX_LEN = 50;
    public final static int URL_MAX_LEN = 500;
}
