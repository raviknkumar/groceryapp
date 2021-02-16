package com.superdaily.groceryapp.service.impl;

import com.superdaily.groceryapp.entity.Item;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    Map<Integer, Item> items;

    private Integer autoIncrementId;
    @PostConstruct
    public void init(){
        items = new HashMap<>();
        autoIncrementId = 1;
    }

    @Override
    public Optional<Item> findById(Integer id) {
        Item item = items.get(id);
        if(item == null)
            return Optional.empty();
        return Optional.of(item);
    }

    @Override
    public Item deleteById(Integer id) throws NotFoundException {
        Optional<Item> itemOptional = findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("No item found with given id:" + id);
        return items.remove(id);
    }

    @Override
    public Item create(Item entity) {
        entity.setId(autoIncrementId);
        items.put(autoIncrementId, entity);
        incrementId();
        return entity;
    }

    private void incrementId() {
        autoIncrementId++;
    }

    @Override
    public Item update(Item entity) {
        items.put(entity.getId(), entity);
        return entity;
    }
}
