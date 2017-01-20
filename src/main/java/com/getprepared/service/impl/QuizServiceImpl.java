package com.getprepared.service.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import com.getprepared.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.QUIZ_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.USER_SERVICE;

/**
 * Created by koval on 15.01.2017.
 */
public class QuizServiceImpl extends AbstractService implements QuizService {

    private static final Logger LOG = Logger.getLogger(QuizServiceImpl.class);

    public QuizServiceImpl() { }

    @Override
    public void save(final Quiz quiz) throws ValidationException, EntityExistsException {
        try {
            getValidation().validateQuiz(quiz);

            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            quizDao.save(quiz);
            getTransactionManager().commit();
        } catch (ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Quiz findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(id);

            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            final Quiz quiz = quizDao.findById(id);
            getTransactionManager().commit();
            return quiz;
        } catch (ValidationException | EntityNotFoundException e) {
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
    public List<Quiz> findByUserEmail(final String email) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateEmail(email);

            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            final List<Quiz> quizzes = quizDao.findByUserEmail(email);
            getTransactionManager().commit();
            return quizzes;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Quiz> findByUserId(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            final List<Quiz> quizzes = quizDao.findByUserId(id);
            getTransactionManager().commit();
            return quizzes;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void assign(final Long quizId, final Long userId) throws ValidationException, EntityNotFoundException,
                                                                                EntityExistsException  {
        try {
            getValidation().validateId(quizId);
            getValidation().validateId(userId);

            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            final Quiz quiz = quizDao.findById(quizId);
            final UserService userService = getUserService();
            final User user = userService.findById(userId);
            quizDao.assign(quiz.getId(), user.getId());
            getTransactionManager().commit();
        } catch (ValidationException | EntityNotFoundException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void remove(final Quiz quiz) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateQuiz(quiz);

            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            quizDao.remove(quiz.getId());
            getTransactionManager().commit();
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private QuizDao getDao() {
        return getDaoFactory().getDao(QUIZ_DAO, QuizDao.class);
    }

    private UserService getUserService() {
        return ServiceFactory.getInstance().getService(USER_SERVICE, UserService.class);
    }
}
