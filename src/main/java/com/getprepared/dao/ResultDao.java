package com.getprepared.dao;

import com.getprepared.domain.Result;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface ResultDao {

    void save(Result result);

    Result findById(Long id) throws EntityNotFoundException;

    List<Result> findByUserEmail(String email);
}
