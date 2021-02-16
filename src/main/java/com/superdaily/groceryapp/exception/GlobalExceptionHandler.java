package com.superdaily.groceryapp.exception;

import com.superdaily.groceryapp.config.ApplicationConfig;
import com.superdaily.groceryapp.models.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = {"com.superdaily.groceryapp"})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderFulfilmentException.class)
    public @ResponseBody
    ApiResponse handleException(OrderFulfilmentException ex) {
        ApiResponse apiOutput = new ApiResponse();
        apiOutput.setSuccess(false);
        apiOutput.setErrorCode(ApplicationConfig.ORDER_FULLFILMENT_ERROR_CODE);
        apiOutput.setErrorMessage(ex.getMessage());
        log.error("Exception occured", ex);
        return apiOutput;
    }

    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    ApiResponse handleNotFoundException(NotFoundException ex) {
        ApiResponse apiOutput = new ApiResponse();
        apiOutput.setSuccess(false);
        apiOutput.setErrorCode(ApplicationConfig.NOT_FOUND_ERROR_CODE);
        apiOutput.setErrorMessage(ex.getMessage());
        log.error("Exception occured", ex);
        return apiOutput;
    }
}
