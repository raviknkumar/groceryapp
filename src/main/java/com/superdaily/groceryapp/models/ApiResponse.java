package com.superdaily.groceryapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    public boolean success = true;
    public T data;
    public Integer errorCode;
    private String errorMessage;

    public ApiResponse(T data) {
        this.data =data;
    }
}
