package com.getprepared.service.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.AnswerService;

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
