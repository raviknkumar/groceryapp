package com.superdaily.groceryapp.entity;

import com.superdaily.groceryapp.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest extends AuditEntity {
    private Integer customerId;
    private Date deliveryDate;
    private Integer wareHouseId;
    private List<ItemDto> items;
}
