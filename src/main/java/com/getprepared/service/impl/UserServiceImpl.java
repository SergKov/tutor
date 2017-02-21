package com.getprepared.service.impl;

import com.getprepared.dao.UserDao;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.ResultService;
import com.getprepared.service.UserService;
import com.getprepared.utils.PasswordEncoder;
import com.getprepared.utils.factory.UtilsFactory;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.USER_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.RESULT_SERVICE;
import static com.getprepared.constant.UtilsConstant.PASSWORD_ENCODER;

/**
 * Created by koval on 14.01.2017.
 */
public class UserServiceImpl extends AbstractService implements UserService {

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
            throw e;
        }
    }

    @Override
    public User signInStudent(final String email, final String password) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findByStudentEmail(email);

            checkPassword(password, user);

            final List<Result> userResults = findAllResults(user.getId());
            user.setResults(userResults);
            getTransactionManager().commit();
            return user;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public User signInTutor(final String email, final String password) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findByTutorEmail(email);

            checkPassword(password, user);
            getTransactionManager().commit();
            return user;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    private void checkPassword(final String password, final User user) throws EntityNotFoundException {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            final String errorMsg = String.format("%s with this password %s does not exist", user.getRole(), password);
            throw new EntityNotFoundException(errorMsg);
        }
    }

    private List<Result> findAllResults(final Long id) {
        final ResultService resultService = getResultService();
        return resultService.findByUserId(id);
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
            throw e;
        }
    }

    private UserDao getDao() {
        return getDaoFactory().getDao(USER_DAO, UserDao.class);
    }

    private ResultService getResultService() {
        return ServiceFactory.getInstance().getService(RESULT_SERVICE, ResultService.class);
    }
}
