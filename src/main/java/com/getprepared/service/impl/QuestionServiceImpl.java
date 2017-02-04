package com.getprepared.service.impl;

import com.getprepared.dao.QuestionDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import org.apache.log4j.Logger;

import java.util.*;

import static com.getprepared.constant.ServerConstants.DAOS.QUESTION_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.ANSWER_SERVICE;

/**
 * Created by koval on 14.01.2017.
 */
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    private static final Logger LOG = Logger.getLogger(QuestionServiceImpl.class);

    public QuestionServiceImpl() { }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void save(final Question question) throws EntityExistsException {
        try {
            getTransactionManager().begin();

            final QuestionDao questionDao = getDao();
            questionDao.save(question);

            final AnswerService answerService = getAnswerService();
            final List<Answer> answers = question.getAnswers();

            for (Answer answer : answers) {
                answerService.save(answer);
            }

            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Question findById(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuestionDao questionDao = getDao();
            final Question question = questionDao.findById(id);
            final AnswerService answerService = getAnswerService();
            final List<Answer> answers = answerService.findByQuestionId(question.getId());
            question.setAnswers(answers);
            getTransactionManager().commit();
            return question;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Question> findByQuizId(final Long id) {

        getTransactionManager().begin();
        final QuestionDao questionDao = getDao();
        final List<Question> questions = questionDao.findByQuizId(id);
        final AnswerService answerService = getAnswerService();

        for (Question question : questions) {
            final Long questionId = question.getId();
            final List<Answer> answers = answerService.findByQuestionId(questionId);
            question.setAnswers(answers);
        }

        getTransactionManager().commit();
        return questions;

    }

    @Override
    public void remove(final Question question) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuestionDao questionDao = getDao();
            questionDao.removeById(question.getId());
            getTransactionManager().commit();
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<TestQuestion> startTest(final Long quizId) {
        final List<Question> questions = findByQuizId(quizId);
        final List<TestQuestion> testQuestions = new ArrayList<>();
        questions.forEach(question -> testQuestions.add(new TestQuestion(question, Collections.EMPTY_LIST)));
        return testQuestions;
    }

    private QuestionDao getDao() {
        return getDaoFactory().getDao(QUESTION_DAO, QuestionDao.class);
    }

    private AnswerService getAnswerService() {
        return ServiceFactory.getInstance().getService(ANSWER_SERVICE, AnswerService.class);
    }
}
