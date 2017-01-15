package com.getprepared.service;

import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;

import java.util.List;

/**
 * Created by koval on 14.01.2017.
 */
public interface SpecialityService {

    void save(Speciality speciality) throws ValidationException, EntityExistsException;

    Speciality findById(Long id) throws ValidationException, EntityNotFoundException;

    List<Speciality> findAll();
}
