package com.getprepared.core.service.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;

import java.util.List;

/**
 * Created by koval on 14.01.2017.
 */
@Component("answerService")
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    @Inject
    private AnswerDao answerDao;

    public AnswerServiceImpl() { }

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        try {
            answerDao.save(answer);
        } catch (final EntityExistsException e) {
            throw e;
        }
    }

    @Override
    public void save(final List<Answer> answers) throws EntityExistsException {
        try {
            transactionManager.begin();
            answerDao.saveBatch(answers);
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        try {
            return answerDao.findById(id);
        } catch (final EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        return answerDao.findByQuestionId(questionId);
    }
}
