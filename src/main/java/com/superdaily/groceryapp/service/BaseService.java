package com.superdaily.groceryapp.service;

import com.superdaily.groceryapp.entity.BaseEntity;
import com.superdaily.groceryapp.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity> {

    default E createOrUpdate(E entity) throws NotFoundException {
        if(entity.getId() == null){
            entity=create(entity);
        } else {
            Optional<E> entityOpt = findById(entity.getId());
            if(!entityOpt.isPresent())
                throw new NotFoundException("No Entity found with given id:"+ entity.getId());
            entity=update(entity);
        }
        return entity;
    }

    default List<E> createOrUpdate(List<E> entities) throws NotFoundException {
        List<E> updatedList = new ArrayList<>();
        for(E entity : entities)
            updatedList.add(createOrUpdate(entity));
        return updatedList;
    }

    Optional<E> findById(Integer id);
    E deleteById(Integer id) throws NotFoundException;

    E create(E entity);
    E update(E entity);
}
