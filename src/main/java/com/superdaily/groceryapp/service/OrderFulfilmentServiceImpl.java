package com.superdaily.groceryapp.service;

import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.exception.OrderFulfilmentException;
import com.superdaily.groceryapp.service.impl.OrderPlacementValidationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderFulfilmentServiceImpl implements OrderFulfilmentService, BaseService<OrderRequest> {

    @Autowired List<? extends OrderPlacementValidationStrategy> orderPlacementValidationStrategies;
    @Autowired private OrderFulfilmentService orderFulfillmentStrategy;
    @Autowired private InventoryService inventoryService;

    @PostConstruct
    private void init(){

    }

    @Override
    //todo
    public Optional<OrderRequest> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public OrderRequest deleteById(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public OrderRequest create(OrderRequest entity) {
        return null;
    }

    @Override
    public OrderRequest update(OrderRequest entity) {
        return null;
    }

    @Override
    public boolean canFulfillOrder(OrderRequest orderRequest) throws OrderFulfilmentException, NotFoundException {
        for (OrderPlacementValidationStrategy validationStrategy : orderPlacementValidationStrategies){
            if(!validationStrategy.canFulfillOrder(orderRequest))
                return false;
        }
        return true;
    }

    @Override
    public void reserveOrder(OrderRequest orderRequest) throws NotFoundException, OrderFulfilmentException {
        if(!canFulfillOrder(orderRequest)){
            throw new OrderFulfilmentException("Insufficient Quantities");
        }
        inventoryService.updateInventory(orderRequest);
    }
}
