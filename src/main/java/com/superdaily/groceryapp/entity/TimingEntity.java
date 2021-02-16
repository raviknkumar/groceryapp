package com.superdaily.groceryapp.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TimingEntity extends BaseEntity{
    private Date createdAt;
    public Date updatedAt;
}
