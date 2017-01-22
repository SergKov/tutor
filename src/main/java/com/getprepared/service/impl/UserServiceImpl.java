package com.getprepared.service.impl;

import com.getprepared.dao.UserDao;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.UserService;
import com.getprepared.utils.PasswordEncoder;
import com.getprepared.utils.factory.UtilsFactory;
import org.apache.log4j.Logger;

import static com.getprepared.constant.ServerConstants.DAOS.USER_DAO;
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
    public User findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateId(id);

            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findById(id);
            getTransactionManager().commit();
            return user;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User findByEmail(final String email) throws ValidationException, EntityNotFoundException {
        try {
            getValidation().validateEmail(email);

            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findByEmail(email);
            getTransactionManager().commit();
            return user;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User signIn(final String email, final String password) throws EntityNotFoundException {

        final String encodedPassword = passwordEncoder.encode(password);

        try {
            getTransactionManager().begin();
            final UserDao userDao = getDao();
            final User user = userDao.findByCredentials(email, encodedPassword);
            getTransactionManager().commit();
            return user;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void signUp(final User user) throws ValidationException, EntityExistsException {
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

    @Override
    public void update(final User user) throws ValidationException, EntityExistsException {
        try {
            getValidation().validateUser(user);
            getValidation().validateId(user.getId());

            getTransactionManager().begin();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            final UserDao userDao = getDao();
            userDao.update(user);
            getTransactionManager().commit();
        } catch (ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    private UserDao getDao() {
        return getDaoFactory().getDao(USER_DAO, UserDao.class);
    }
}
