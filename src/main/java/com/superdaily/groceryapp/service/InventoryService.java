package com.superdaily.groceryapp.service;

import com.superdaily.groceryapp.dto.ItemDto;
import com.superdaily.groceryapp.entity.Inventory;
import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface InventoryService extends BaseService<Inventory> {

    List<Inventory> findByItemIdAndDeliveryDateAndWareHouseId(List<Integer> itemIds, Date deliveryDate, Integer wareHouseId);
    Inventory findByItemIdAndDeliveryDateAndWareHouseId(Integer itemId, Date deliveryDate, Integer warehouseId);
    List<Inventory> updateInventory(OrderRequest orderRequest) throws NotFoundException;
}
