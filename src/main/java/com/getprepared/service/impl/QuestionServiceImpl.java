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

import java.util.Collections;
import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.QUESTION_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.ANSWER_SERVICE;

/**
 * Created by koval on 14.01.2017.
 */
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    private static final Logger LOG = Logger.getLogger(QuestionServiceImpl.class);

    @Override
    public void save(Question question) throws ValidationException, EntityExistsException {
        try {
            getTransactionManager().begin();
            getValidation().validateQuestion(question);

            final QuestionDao questionDao = getQuestionDao();
            questionDao.save(question);

            final AnswerService answerService = getAnswerService();
            final List<Answer> answers = question.getAnswers();

            for (Answer answer : answers) {
                answerService.save(answer);
            }

            getTransactionManager().commit();
        } catch (final ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Question findById(Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);

            final QuestionDao questionDao = getQuestionDao();
            final Question question = questionDao.findById(id);

            getTransactionManager().commit();
            return question;
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Question> findByQuizId(Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);

            final QuestionDao questionDao = getQuestionDao();
            final List<Question> questions = questionDao.findByQuizId(id);

            getTransactionManager().commit();
            return Collections.unmodifiableList(questions);
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void remove(Question question) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateEntity(question);

            final QuestionDao questionDao = getQuestionDao();
            questionDao.removeById(question.getId());

            final AnswerService answerService = getAnswerService();
            answerService.removeByQuestionId(question.getId());
            getTransactionManager().commit();
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private QuestionDao getQuestionDao() {
        return getDaoFactory().getDao(QUESTION_DAO, QuestionDao.class);
    }

    private AnswerService getAnswerService() {
        return ServiceFactory.getInstance().getService(ANSWER_SERVICE, AnswerService.class);
    }
}
