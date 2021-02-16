package com.superdaily.groceryapp.service.impl;

import com.superdaily.groceryapp.dto.ItemDto;
import com.superdaily.groceryapp.entity.Inventory;
import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@Service
public class InventoryServiceImpl implements InventoryService {

    private Integer autoIncrementId;
    private Map<Integer, Inventory> inventory;

    @PostConstruct
    public void init(){
        autoIncrementId = 1;
        inventory = new HashMap<>();
    }

    @Override
    public Optional<Inventory> findById(Integer id) {
        Inventory inventory = this.inventory.get(id);
        if(inventory == null)
            return Optional.empty();
        return Optional.of(inventory);
    }

    @Override
    public Inventory deleteById(Integer id) throws NotFoundException {
        Optional<Inventory> inventoryOptional = findById(id);
        if(!inventoryOptional.isPresent())
            throw new NotFoundException("No Inventory Details found with id: "+ id);
        return null;
    }

    @Override
    public Inventory create(Inventory entity) {
        entity.setId(autoIncrementId);
        inventory.put(autoIncrementId, entity);
        incrementId();
        return entity;
    }

    @Override
    public Inventory update(Inventory entity) {
        inventory.put(autoIncrementId, entity);
        return entity;
    }

    @Override
    public List<Inventory> findByItemIdAndDeliveryDateAndWareHouseId(List<Integer> itemIds, Date deliveryDate,
                                                                     Integer wareHouseId) {
        List<Inventory> inventoryList = new ArrayList<>();
        for (Integer itemId : itemIds){
            Inventory inventory = findByItemIdAndDeliveryDateAndWareHouseId(itemId, deliveryDate, wareHouseId);
            if(inventory != null)
            inventoryList.add(inventory);
        }
        return inventoryList;
    }

    @Override
    public Inventory findByItemIdAndDeliveryDateAndWareHouseId(Integer itemId, Date deliveryDate, Integer warehouseId) {
        Stream<Inventory> inventoryStream = inventory.values()
                .stream();
        if(itemId != null){
            inventoryStream = inventoryStream.filter(inv -> inv.getItem().getId().equals(itemId));
        }
        if(deliveryDate != null){
            inventoryStream = inventoryStream.filter(inv -> inv.getDate().equals(deliveryDate));
        }
        if(warehouseId != null){
            inventoryStream = inventoryStream.filter(inv -> inv.getWareHouseId().equals(warehouseId));
        }

        return inventoryStream.findFirst().orElse(null);
    }

    @Override
    public List<Inventory> updateInventory(OrderRequest orderRequest) throws NotFoundException {

        Date deliveryDate = orderRequest.getDeliveryDate();
        Integer wareHouseId = orderRequest.getWareHouseId();

        List<Inventory> inventoryListToUpdate = new ArrayList<>();

        for (ItemDto itemDto : orderRequest.getItems()){
            Inventory inventory = findByItemIdAndDeliveryDateAndWareHouseId(itemDto.getId(),
                    deliveryDate, wareHouseId);
            Double inventoryItemCount = inventory.getItemCount();
            Double itemCount = itemDto.getQuantity();
            inventory.setItemCount(inventoryItemCount - itemCount);
            inventoryListToUpdate.add(inventory);
        }

        return createOrUpdate(inventoryListToUpdate);
    }

    private void incrementId(){
        autoIncrementId++;
    }
}
