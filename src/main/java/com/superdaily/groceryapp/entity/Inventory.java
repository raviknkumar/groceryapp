package com.superdaily.groceryapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory extends AuditEntity{
    private Item item;
    private Date date;
    private Double itemCount;
    private Integer wareHouseId;
}
