package com.superdaily.groceryapp.controller;

import com.superdaily.groceryapp.entity.Item;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.models.ApiResponse;
import com.superdaily.groceryapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired private ItemService itemService;

    @PostMapping("")
    public ApiResponse<Item> createOrUpdate(@RequestBody Item item) throws NotFoundException {
        item= itemService.createOrUpdate(item);
        return new ApiResponse<>(item);
    }
}
