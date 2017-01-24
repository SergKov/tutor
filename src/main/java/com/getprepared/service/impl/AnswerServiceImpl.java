package com.getprepared.service.impl;

import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.AnswerService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.ANSWER_DAO;

/**
 * Created by koval on 14.01.2017.
 */
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    private static final Logger LOG = Logger.getLogger(AnswerServiceImpl.class);

    public AnswerServiceImpl() { }

    @Override
    public void save(final Answer answer) throws ValidationException, EntityExistsException {
        try {
            getValidation().validateAnswer(answer);

            getTransactionManager().begin();
            final AnswerDao answerDao = getDao();
            answerDao.save(answer);
            getTransactionManager().commit();
        } catch (ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Answer findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(id);

            getTransactionManager().begin();
            final AnswerDao answerDao = getDao();
            final Answer answer = answerDao.findById(id);
            getTransactionManager().commit();
            return answer;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(questionId);

            getTransactionManager().begin();
            final AnswerDao answerDao = getDao();
            final List<Answer> answers = answerDao.findByQuestionId(questionId);
            getTransactionManager().commit();
            return answers;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void removeByQuestionId(final Long questionId) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(questionId);

            getTransactionManager().begin();
            final AnswerDao answerDao = getDao();
            answerDao.removeByQuestionId(questionId);
            getTransactionManager().commit();
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private AnswerDao getDao() {
        return getDaoFactory().getDao(ANSWER_DAO, AnswerDao.class);
    }
}
