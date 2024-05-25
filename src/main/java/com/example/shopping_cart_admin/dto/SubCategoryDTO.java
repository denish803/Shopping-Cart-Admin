package com.example.shopping_cart_admin.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class SubCategoryDTO {

    private int id;
    private String name;
    private int categoryId;
}
