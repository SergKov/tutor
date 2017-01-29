package com.getprepared.service.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.QUIZ_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.USER_SERVICE;

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
            getTransactionManager().commit();
            return quiz;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Quiz> findAll() {
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
            final QuestionService questionService = getQuestionService();
            questionService.removeByQuizId(quiz.getId());
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
        return ServiceFactory.getInstance().getService(QUESTION_SERVICE, QuestionService.class);
    }
}
