package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.domain.Answer;

import java.util.List;

/**
 * Created by koval on 14.01.2017.
 */
@Service("answerService")
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    @Inject
    private AnswerDao answerDao;

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        try {
            transactionManager.begin();
            answerDao.save(answer);
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
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
            transactionManager.begin();
            final Answer answer = answerDao.findById(id);
            transactionManager.commit();
            return answer;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        transactionManager.begin();
        final List<Answer> answers = answerDao.findByQuestionId(questionId);
        transactionManager.commit();
        return answers;
    }
}
