package com.superdaily.groceryapp.service.impl;

import com.superdaily.groceryapp.config.ApplicationConfig;
import com.superdaily.groceryapp.dto.ItemDto;
import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.OrderFulfilmentException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryCheckFullfilmentStrategyImpl implements OrderPlacementValidationStrategy {

    @Override
    public boolean canFulfillOrder(OrderRequest orderRequest) throws OrderFulfilmentException {
        List<ItemDto> items = orderRequest.getItems();
        Map<String, List<ItemDto>> itemCategoryToItemsMap = items.stream()
                .collect(Collectors.groupingBy(ItemDto::getCategory));

        int quantitySumPerItemCategory = 0;
        for (String category : itemCategoryToItemsMap.keySet()) {
            List<ItemDto> itemList = itemCategoryToItemsMap.get(category);
            for (ItemDto item : itemList){
                quantitySumPerItemCategory += item.getQuantity();
                if(quantitySumPerItemCategory > ApplicationConfig.MAX_CATEGORY_WEIGHT){
                    throw new OrderFulfilmentException("Category Quantity exceeded maximum limit!");
                }
            }
        }
        return true;
    }
}
