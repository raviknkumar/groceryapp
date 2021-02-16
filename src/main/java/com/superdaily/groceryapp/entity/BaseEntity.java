package com.superdaily.groceryapp.entity;


import lombok.Data;

@Data
public class BaseEntity {
    private Integer id;
    private boolean deleted;
}
