package com.superdaily.groceryapp.entity;

import lombok.Data;

@Data
public class AuditEntity extends TimingEntity {
    private Integer createdBy;
    private Integer updatedBy;
}
