package com.getprepared.persistence.dao;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.database.pagination.Page;
import com.getprepared.persistence.database.pagination.Pageable;
import com.getprepared.persistence.domain.Result;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by koval on 03.01.2017.
 */
public interface ResultDao {

    void save(Result result) throws EntityExistsException;

    Result findById(Long id) throws EntityNotFoundException;

    List<Result> findAllByUserId(Long id, Pageable page, Long currentPage);

    List<Result> findAllByQuizId(Long quizId, Pageable page, Long currentPage);
}
