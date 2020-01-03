package com.ms.appservice.controller;

import com.ms.appservice.model.BaseModel;
import com.ms.appservice.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Slf4j
public abstract class BaseController<S extends BaseService<T>, T extends BaseModel> {

    @Autowired
    public
    S service;

    @GetMapping
    @ResponseBody
       public Page<T> getPageable(Pageable pageable) {

        log.info("List all active entity");
        return service.findAllActive(pageable);
    }
    @GetMapping(value = "/list")
    public List<T> getAll(Pageable pageable) {
        log.info("List all entity operation");
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public T insert(@Valid @RequestBody T item) {
        log.info("create entity: "+item);
        return service.insert(item);
    }

    @PostMapping("/saveall")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<T> saveAll(@Valid @RequestBody Iterable<T> list) {
        list.forEach(x->log.info("create entity: "+x));
        return service.saveAll(list);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public T findById(@PathVariable("uuid") UUID id) {
        log.info("fetch operation of entity id: "+id);
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        log.warn("Delete operation of entity id: "+id);
        service.delete(id);
        return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public T update(@RequestBody T item) {
        log.info("Update operation of entity id: "+item.getId());
        return service.update(item);
    }
}
