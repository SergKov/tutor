package com.getprepared.service;

import com.getprepared.domain.Result;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface ResultService {

    void save(Result result) throws EntityExistsException;

    Result findById(Long id) throws EntityNotFoundException;

    List<Result> findByUserId(Long userId);
}
