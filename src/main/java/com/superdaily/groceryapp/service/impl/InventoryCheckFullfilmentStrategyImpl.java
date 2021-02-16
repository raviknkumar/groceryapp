package com.superdaily.groceryapp.service.impl;

import com.superdaily.groceryapp.dto.ItemDto;
import com.superdaily.groceryapp.entity.Inventory;
import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class InventoryCheckFullfilmentStrategyImpl implements OrderPlacementValidationStrategy {

    @Autowired private InventoryService inventoryService;

    @Override
    public boolean canFulfillOrder(OrderRequest orderRequest) throws NotFoundException {
        Date deliveryDate = orderRequest.getDeliveryDate();
        Integer wareHouseId = orderRequest.getWareHouseId();
        List<ItemDto> items = orderRequest.getItems();

        // todo: implement converter if needed

        for (ItemDto itemDto : items){
            Inventory inventory = inventoryService.findByItemIdAndDeliveryDateAndWareHouseId(itemDto.getId(),
                    deliveryDate, wareHouseId);
            if(inventory == null)
                throw new NotFoundException("No Inventory found for the item id:"+itemDto.getId());
            if(inventory.getItemCount() < itemDto.getQuantity()){
                log.info("Item id {} cant be fullfilled {} for InventoryId", itemDto.getId(), inventory.getId());
                return false;
            }
        }
        return true;
    }
}
