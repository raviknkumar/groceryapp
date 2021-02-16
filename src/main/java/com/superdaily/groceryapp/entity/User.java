package com.superdaily.groceryapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AuditEntity {
    private String userName;
    private String email;
    private String password;
}
