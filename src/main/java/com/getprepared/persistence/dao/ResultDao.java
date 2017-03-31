package com.getprepared.persistence.dao;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Result;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface ResultDao {

    void save(Result result) throws EntityExistsException;

    Result findById(Long id) throws EntityNotFoundException;

    List<Result> findByUserId(Long id, PageableData page);

    List<Result> findByQuizId(Long quizId, PageableData page);
}
