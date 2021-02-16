package com.superdaily.groceryapp.service;

import com.superdaily.groceryapp.entity.User;

public interface UserService extends BaseService<User>{
    public User signUp(User user);
    public User login(User user);
}
