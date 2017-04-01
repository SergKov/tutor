package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.PasswordEncoder;
import com.getprepared.persistence.dao.UserDao;
import com.getprepared.persistence.domain.User;

/**
 * Created by koval on 14.01.2017.
 */
@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private ResultService resultService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(final Long id) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findById(id);
            transactionManager.commit();
            return user;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public User signInStudent(final String email, final String password) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findByStudentEmail(email);
            checkPassword(password, user);
            transactionManager.commit();
            return user;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public User signInTutor(final String email, final String password) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findByTutorEmail(email);
            checkPassword(password, user);
            transactionManager.commit();
            return user;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    private void checkPassword(final String password, final User user) throws EntityNotFoundException {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            final String errorMsg = String.format("%s with this password %s does not exist.", user.getRole(), password);
            throw new EntityNotFoundException(errorMsg);
        }
    }

    @Override
    public void signUp(final User user) throws EntityExistsException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            transactionManager.begin();
            userDao.save(user);
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public void updateStudentPassword(final String email, final String enteredPassword, final String newPassword)
                                                                                        throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findByStudentEmail(email);
            checkPassword(enteredPassword, user.getPassword());
            userDao.updateTutorPassword(newPassword);
            transactionManager.commit();
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public void updateTutorPassword(final String email, final String enteredPassword, final String newPassword)
                                                                                        throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findByStudentEmail(email);
            checkPassword(enteredPassword, user.getPassword());
            userDao.updateStudentPassword(newPassword);
            transactionManager.commit();
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    private void checkPassword(final String enteredPassword, final String oldPassword) throws EntityNotFoundException {
        if (!passwordEncoder.matches(enteredPassword, oldPassword)) {
            throw new EntityNotFoundException("Old password is incorrect.");
        }
    }
}
