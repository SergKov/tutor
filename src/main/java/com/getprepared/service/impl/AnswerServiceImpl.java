package com.getprepared.service.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.AnswerService;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.ANSWER_DAO;

/**
 * Created by koval on 14.01.2017.
 */
@Bean("answerService")
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    @Inject
    private AnswerDao answerDao;

    public AnswerServiceImpl() { }

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            answerDao.save(answer);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public void save(final List<Answer> answers) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            answerDao.saveBatch(answers);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final Answer answer = answerDao.findById(id);
            getTransactionManager().commit();
            return answer;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        getTransactionManager().begin();
        final List<Answer> answers = answerDao.findByQuestionId(questionId);
        getTransactionManager().commit();
        return answers;
    }
}
