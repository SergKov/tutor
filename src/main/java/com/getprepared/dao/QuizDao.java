package com.getprepared.dao;

import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.pagination.Page;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface QuizDao {

    void save(Quiz quiz) throws EntityExistsException;

    Quiz findById(Long id) throws EntityNotFoundException;

    List<Quiz> findByUserId(Long id) throws EntityNotFoundException;

    List<Quiz> findByUserEmail(String email) throws EntityNotFoundException;

    void assign(Long userId, Long quizId) throws EntityExistsException;

    Page<Quiz> findAll(Long page, Long pageSize) throws EntityNotFoundException;

    Page<Quiz> findAllBySpecialityId(Long specialityId, Long page, Long pageSize) throws EntityNotFoundException;

    void remove(Long id) throws EntityNotFoundException;
}
