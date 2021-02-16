package com.superdaily.groceryapp.controller;

import com.superdaily.groceryapp.dto.OrderFulfillmentDto;
import com.superdaily.groceryapp.dto.OrderReservationDto;
import com.superdaily.groceryapp.entity.OrderRequest;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.exception.OrderFulfilmentException;
import com.superdaily.groceryapp.models.ApiResponse;
import com.superdaily.groceryapp.service.OrderFulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.superdaily.groceryapp.config.MessageConstants.ORDER_RESERVE_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/order")
public class OrderRequestController {

    @Autowired private OrderFulfilmentService orderFulfilmentService;

    @PostMapping("/fulfill")
    public ApiResponse<OrderFulfillmentDto> canFullfillOrder(@RequestBody OrderRequest orderRequest) throws OrderFulfilmentException, NotFoundException {
        boolean orderFulfillStatus = orderFulfilmentService.canFulfillOrder(orderRequest);
        OrderFulfillmentDto orderFulfillmentDto = new OrderFulfillmentDto(orderFulfillStatus);
        return new ApiResponse<>(orderFulfillmentDto);
    }

    /**
     * returns success
     * @param orderRequest
     * @return
     * @throws NotFoundException - handled By Global Exception Handler
     * @throws OrderFulfilmentException - handled By Global Exception Handler
     */
    @PostMapping()
    public ApiResponse<OrderReservationDto> placeOrder(@RequestBody OrderRequest orderRequest) throws NotFoundException, OrderFulfilmentException {
        orderFulfilmentService.reserveOrder(orderRequest);
        OrderReservationDto orderReservationDto = OrderReservationDto
                .builder()
                .reserved(true)
                .message(ORDER_RESERVE_SUCCESS_MESSAGE)
                .build();
        return new ApiResponse<>(orderReservationDto);
    }
}
