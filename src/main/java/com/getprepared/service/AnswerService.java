package com.getprepared.service;

import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;

import java.util.List;

/**
 * Created by koval on 09.01.2017.
 */
public interface AnswerService {

    void save(Answer answer) throws ValidationException, EntityExistsException;

    Answer findById(Long id) throws ValidationException, EntityExistsException, EntityNotFoundException;

    List<Answer> findByQuestionId(Long questionId) throws ValidationException, EntityNotFoundException;
}
