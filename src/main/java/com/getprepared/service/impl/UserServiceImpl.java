package com.getprepared.service.impl;

import com.getprepared.dao.UserDao;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.ResultService;
import com.getprepared.service.UserService;
import com.getprepared.utils.PasswordEncoder;
import com.getprepared.utils.factory.UtilsFactory;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.USER_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.RESULT_SERVICE;
import static com.getprepared.constant.UtilsConstant.PASSWORD_ENCODER;

/**
 * Created by koval on 14.01.2017.
 */
public class UserServiceImpl extends AbstractService implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() { }

    @Override
    public void init() {
        super.init();
        passwordEncoder = UtilsFactory.getInstance().getUtil(PASSWORD_ENCODER, PasswordEncoder.class);
    }

    @Override
    public User findById(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findById(id);
            final List<Result> userResults = findAllResults(user.getId());
            user.setResults(userResults);
            getTransactionManager().commit();
            return user;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User signInStudent(final String email, final String password) throws EntityNotFoundException {

        final String encodedPassword = passwordEncoder.encode(password);

        try {
            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findByStudentCredentials(email, encodedPassword);
            final List<Result> userResults = findAllResults(user.getId());
            user.setResults(userResults);
            getTransactionManager().commit();
            return user;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User signInTutor(String email, String password) throws EntityNotFoundException {

        final String encodedPassword = passwordEncoder.encode(password);

        try {
            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findByTutorCredentials(email, encodedPassword);
            getTransactionManager().commit();
            return user;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private List<Result> findAllResults(final Long id) {
        final ResultService resultService = getResultService();
        final List<Result> results = resultService.findByUserId(id);
        return results;
    }

    @Override
    public void signUp(final User user) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            final UserDao userDao = getDao();
            userDao.save(user);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private UserDao getDao() {
        return getDaoFactory().getDao(USER_DAO, UserDao.class);
    }

    private ResultService getResultService() {
        return getServiceFactory().getService(RESULT_SERVICE, ResultService.class);
    }
}
