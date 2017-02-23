package com.getprepared.service.impl;

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
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    public AnswerServiceImpl() { }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            final AnswerDao answerDao = getDao();
            answerDao.save(answer);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public void save(List<Answer> answers) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            final AnswerDao answerDao = getDao();
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
            final AnswerDao answerDao = getDao();
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
        final AnswerDao answerDao = getDao();
        final List<Answer> answers = answerDao.findByQuestionId(questionId);
        getTransactionManager().commit();
        return answers;
    }

    private AnswerDao getDao() {
        return getDaoFactory().getDao(ANSWER_DAO, AnswerDao.class);
    }
}
