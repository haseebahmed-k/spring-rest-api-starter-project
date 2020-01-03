package com.ms.appservice.repository;

import com.ms.appservice.model.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {

    Page<T> findAllByDeleted(Boolean deleted, Pageable pageable);
    List<T> findAllByDeleted(Boolean deleted);
    Page<T> findAllActive(Pageable pageable);

}