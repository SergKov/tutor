package com.getprepared.service.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.QUIZ_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.ANSWER_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;

/**
 * Created by koval on 15.01.2017.
 */
public class QuizServiceImpl extends AbstractService implements QuizService {

    private static final Logger LOG = Logger.getLogger(QuizServiceImpl.class);

    public QuizServiceImpl() { }

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            quizDao.save(quiz);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            final Quiz quiz = quizDao.findById(id);
            final QuestionService questionService = getQuestionService();
            final List<Question> questions = questionService.findByQuizId(id);
            final AnswerService answerService = getAnswerService();

            for (Question question : questions) {
                final Long questionId = question.getId();
                final List<Answer> answers = answerService.findByQuestionId(questionId);
                question.setAnswers(answers);
            }

            getTransactionManager().commit();
            return quiz;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Quiz> findAll()  {
        getTransactionManager().begin();
        final QuizDao quizDao = getDao();
        final List<Quiz> quizzes = quizDao.findAll();
        getTransactionManager().commit();
        return quizzes;
    }

    @Override
    public void remove(final Quiz quiz) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            quizDao.remove(quiz.getId());
            getTransactionManager().commit();
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private QuizDao getDao() {
        return getDaoFactory().getDao(QUIZ_DAO, QuizDao.class);
    }

    private QuestionService getQuestionService() {
        return getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
    }

    private AnswerService getAnswerService() {
        return getServiceFactory().getService(ANSWER_SERVICE, AnswerService.class);
    }
}
