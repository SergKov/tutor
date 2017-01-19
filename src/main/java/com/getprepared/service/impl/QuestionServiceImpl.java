package com.getprepared.service.impl;

import com.getprepared.dao.QuestionDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.QUESTION_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.ANSWER_SERVICE;

/**
 * Created by koval on 14.01.2017.
 */
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    private static final Logger LOG = Logger.getLogger(QuestionServiceImpl.class);

    @Override
    public void save(final Question question) throws ValidationException, EntityExistsException {
        try {
            getTransactionManager().begin();
            getValidation().validateQuestion(question);

            final QuestionDao questionDao = getDao();
            questionDao.save(question);

            final AnswerService answerService = getAnswerService();
            final List<Answer> answers = question.getAnswers();

            for (Answer answer : answers) {
                answerService.save(answer);
            }

            getTransactionManager().commit();
        } catch (ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Question findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);

            final QuestionDao questionDao = getDao();
            final Question question = questionDao.findById(id);

            getTransactionManager().commit();
            return question;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Question> findByQuizId(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);

            final QuestionDao questionDao = getDao();
            final List<Question> questions = questionDao.findByQuizId(id);

            getTransactionManager().commit();
            return questions;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void remove(final Question question) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateEntity(question);

            final QuestionDao questionDao = getDao();
            questionDao.removeById(question.getId());

            final AnswerService answerService = getAnswerService();
            answerService.removeByQuestionId(question.getId());
            getTransactionManager().commit();
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private QuestionDao getDao() {
        return getDaoFactory().getDao(QUESTION_DAO, QuestionDao.class);
    }

    private AnswerService getAnswerService() {
        return ServiceFactory.getInstance().getService(ANSWER_SERVICE, AnswerService.class);
    }
}
