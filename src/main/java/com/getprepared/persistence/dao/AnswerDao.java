package com.getprepared.persistence.dao;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.domain.Answer;

import java.util.List;

/**
 * Created by koval on 03.01.2017.
 */
public interface AnswerDao {

    void save(Answer answer) throws EntityExistsException;

    void saveBatch(List<Answer> answers) throws EntityExistsException;

    Answer findById(Long id) throws EntityNotFoundException;

    List<Answer> findByQuestionId(Long questionId);

    List<Answer> findByQuestionIdRandom(Long questionId);
}
