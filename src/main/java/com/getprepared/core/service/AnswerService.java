package com.getprepared.core.service;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.domain.Answer;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface AnswerService {

    void save(Answer answer) throws EntityExistsException;

    void save(List<Answer> answers) throws EntityExistsException;

    Answer findById(Long id) throws EntityNotFoundException;

    List<Answer> findByQuestionId(Long questionId);
}
