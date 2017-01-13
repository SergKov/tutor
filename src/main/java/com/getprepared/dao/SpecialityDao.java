package com.getprepared.dao;

import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;

import java.util.List;

/**
 * Created by koval on 13.01.2017.
 */
public interface SpecialityDao {

    void save(Speciality speciality) throws EntityExistsException;

    List<Speciality> findAll();
}
