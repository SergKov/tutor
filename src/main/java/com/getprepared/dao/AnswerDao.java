package com.getprepared.dao;

import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface AnswerDao {

    void save(Answer answer) throws EntityExistsException;

    Answer findById(Long id) throws EntityNotFoundException;

    List<Answer> findByQuestionId(Long questionId) throws EntityNotFoundException;

    void removeByQuestionId(Long questionId) throws EntityExistsException;
}
