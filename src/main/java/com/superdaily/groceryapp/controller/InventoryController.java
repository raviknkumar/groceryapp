package com.superdaily.groceryapp.controller;

import com.superdaily.groceryapp.entity.Inventory;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.models.ApiResponse;
import com.superdaily.groceryapp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired private InventoryService inventoryService;

    @PostMapping()
    public ApiResponse<Inventory> createOrUpdate(@RequestBody Inventory inventory) throws NotFoundException {
        inventory = inventoryService.createOrUpdate(inventory);
        return new ApiResponse<>(inventory);
    }
}
