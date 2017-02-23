package com.getprepared.service;

import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;

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
