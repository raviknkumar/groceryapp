package com.superdaily.groceryapp.service;

import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.exception.OrderFulfilmentException;
import com.superdaily.groceryapp.service.impl.OrderPlacementValidationStrategy;

public interface OrderFulfilmentService extends OrderPlacementValidationStrategy {
    void reserveOrder(OrderRequest orderRequest) throws OrderFulfilmentException, NotFoundException;
}
