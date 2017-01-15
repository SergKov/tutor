package com.getprepared.service.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.QUIZ_DAO;

/**
 * Created by koval on 15.01.2017.
 */
public class QuizServiceImpl extends AbstractService implements QuizService {

    private static final Logger LOG = Logger.getLogger(QuizServiceImpl.class);

    public QuizServiceImpl() { }

    @Override
    public void save(Quiz quiz) throws ValidationException, EntityExistsException {
        try {
            getTransactionManager().begin();
            getValidation().validateQuiz(quiz);
            final QuizDao quizDao = getQuizDao();
            quizDao.save(quiz);
            getTransactionManager().commit();
        } catch (final ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Quiz findById(Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);
            final QuizDao quizDao = getQuizDao();
            final Quiz quiz = quizDao.findById(id);
            getTransactionManager().commit();
            return quiz;
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Quiz> findAll() {
        //TODO
        return null;
    }

    @Override
    public List<Quiz> findAllByUserEmail(String email) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateEmail(email);
            final QuizDao quizDao = getQuizDao();
            final List<Quiz> quizzes = quizDao.findByUserEmail(email);
            getTransactionManager().commit();
            return quizzes;
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Quiz> findAllByUserId(Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);
            final QuizDao quizDao = getQuizDao();
            final List<Quiz> quizzes = quizDao.findByUserId(id);
            getTransactionManager().commit();
            return quizzes;
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void institute(Long id, User user) {
        //TODO
    }

    @Override
    public void remove(Quiz quiz) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateQuiz(quiz);
            final QuizDao quizDao = getQuizDao();
            quizDao.remove(quiz.getId());
            getTransactionManager().commit();
        } catch (final ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    public QuizDao getQuizDao() {
        return getDaoFactory().getDao(QUIZ_DAO, QuizDao.class);
    }
}
