package com.getprepared.dao;

import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.pagination.Page;

import java.util.List;

/**
 * Created by koval on 13.01.2017.
 */
public interface SpecialityDao {

    void save(Speciality speciality) throws EntityExistsException;

    Speciality findById(Long id) throws EntityNotFoundException;

    Page<Speciality> findAll(Long page, Long pageSize) throws EntityNotFoundException;
}
