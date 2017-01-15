package com.getprepared.service.impl;

import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.infrastructure.tm.TransactionManager;
import com.getprepared.service.AnswerService;
import org.apache.log4j.Logger;

import java.util.Collections;
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
            getTransactionManager().begin();
            getValidation().validateAnswer(answer);
            final AnswerDao answerDao = getAnswerDao();
            answerDao.save(answer);
            getTransactionManager().commit();
        } catch (final ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Answer findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);
            final AnswerDao answerDao = getAnswerDao();
            final Answer answer = answerDao.findById(id);
            getTransactionManager().commit();
            return answer;
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(questionId);
            final AnswerDao answerDao = getAnswerDao();
            final List<Answer> answers = answerDao.findByQuestionId(questionId);
            getTransactionManager().commit();
            return Collections.unmodifiableList(answers);
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void removeByQuestionId(final Long questionId) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(questionId);
            final AnswerDao answerDao = getAnswerDao();
            answerDao.removeByQuestionId(questionId);
            getTransactionManager().commit();
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private AnswerDao getAnswerDao() {
        return getDaoFactory().getDao(ANSWER_DAO, AnswerDao.class);
    }
}
