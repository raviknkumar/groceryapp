package com.superdaily.groceryapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    private String name;
    private String category;
    private Integer id;
    private Double quantity;
}
