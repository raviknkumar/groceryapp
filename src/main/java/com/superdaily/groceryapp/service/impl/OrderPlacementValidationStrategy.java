package com.superdaily.groceryapp.service.impl;

import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.exception.OrderFulfilmentException;

public interface OrderPlacementValidationStrategy {
    boolean canFulfillOrder(OrderRequest orderRequest) throws OrderFulfilmentException, NotFoundException;
}
