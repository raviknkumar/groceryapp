package com.superdaily.groceryapp.service.impl;

import com.superdaily.groceryapp.entity.User;
import com.superdaily.groceryapp.exception.NotFoundException;
import com.superdaily.groceryapp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Map<Integer, User> users;
    private Integer userAutoIncrementId;

    @PostConstruct
    public void initialize(){
        users = new HashMap<>();
        userAutoIncrementId=1;
    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = users.get(id);
        if(user == null)
            return Optional.empty();
        return Optional.of(user);
    }

    @Override
    public User deleteById(Integer id) throws NotFoundException {
        Optional<User> userOptional = findById(id);
        if(!userOptional.isPresent()){
            throw new NotFoundException("User with id: "+ id +" is not found");
        }
        return users.remove(id);
    }

    @Override
    public User create(User entity) {
        entity.setId(userAutoIncrementId);
        users.put(userAutoIncrementId, entity);
        incrementId();
        return entity;
    }

    private void incrementId() {
        userAutoIncrementId++;
    }

    @Override
    public User update(User entity) {
        users.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public User signUp(User user) {
        return create(user);
    }

    @Override
    public User login(User user) {
        return null;
    }
}
