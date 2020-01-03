package com.ms.appservice.service.impl;

import com.ms.appservice.model.BaseModel;
import com.ms.appservice.repository.BaseRepository;
import com.ms.appservice.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    @Autowired
    BaseRepository<T> repository;

    @Override
    public T insert(T item) {
        try {
            return repository.saveAndFlush(item);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            String p =e.getMessage();

        }

        return repository.saveAndFlush(item);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

//    @Override
//    public List<T> searchByName(String keyword) {
//        return repository.findByNameContainingIgnoreCase(keyword);
//    }

    @Override
    public List<T> getAll() {
        return repository.findAllByDeleted(false);
    }

    @Override
    public T findById(UUID id) {
        Optional<T> itemOpt = repository.findById(id);
        if (itemOpt.isPresent()) {
            return itemOpt.get();
        } else {
            return null;
        }

    }

    @Override
    public T delete(UUID id) {
        Optional<T> item = repository.findById(id);
        if (item.isPresent()) {
            deleteSoft(item.get().getId());
            return item.get();
        }
        return null;
    }
    @Override
    public List<T> saveAll(Iterable<T> list) {
        return repository.saveAll(list);
    }

    @Override
    public T deleteSoft(UUID id) {
        Optional<T> item = repository.findById(id);
        if (item.isPresent() && item.get().isDeleted() == false) {
            item.get().setDeleted(true);
            repository.save(item.get());
            return item.get();
        }
        return null;
    }

    @Override
    public T update(T item) {
        return repository.saveAndFlush(item);
    }

//    @Override
//    public T findByName(String name) {
//        return repository.findByName(name);
//    }

//    @Override
//    public boolean exists(String name) {
//        if (repository.findByName(name) != null) {
//            return true;
//        }
//        return false;
//    }
    @Override
    public Page<T> findListPage(Pageable pageable ){
        return  repository.findAll(pageable);

    }
//    @Override
//    public List<T> findByNameContainingIgnoreCase(String keyword){
//        return  repository.findByNameContainingIgnoreCase(keyword);
//    }

    @Override
    public Page<T> findAllActive(Pageable pageable) {
        return  repository.findAllByDeleted(false,pageable);
    }

}
