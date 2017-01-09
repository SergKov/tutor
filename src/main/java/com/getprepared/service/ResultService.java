package com.getprepared.service;

import com.getprepared.domain.Result;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface ResultService {

    void save(Result result);

    Result findById(Long id);

    List<Result> findAllByUserId(Long userId);
}
