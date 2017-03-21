package com.getprepared.core.service;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.domain.Result;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface ResultService {

    void save(Result result) throws EntityExistsException;

    Result findById(Long id) throws EntityNotFoundException;

    List<Result> findByUserId(Long userId);
}
