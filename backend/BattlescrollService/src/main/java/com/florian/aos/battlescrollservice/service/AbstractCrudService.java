package com.florian.aos.battlescrollservice.service;

import com.florian.aos.battlescrollservice.exception.NotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class AbstractCrudService <T, ID> {

    protected final CrudRepository<T, ID> repository;
    protected final String entityName;

    protected AbstractCrudService(CrudRepository<T, ID> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    public T getById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName));
    }

    public List<T> getAll(){
        return (List<T>) repository.findAll();
    }

    public T save(T entity){
        return repository.save(entity);
    }

    public boolean deleteById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName));
        repository.delete(entity);
        return true;
    }
}
