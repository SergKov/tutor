package com.getprepared.dao;

import com.getprepared.domain.ResultDTO;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface ResultDao {

    void save(ResultDTO result);

    ResultDTO findById(Long id);

    List<ResultDTO> findByUserEmail(String email);
}
