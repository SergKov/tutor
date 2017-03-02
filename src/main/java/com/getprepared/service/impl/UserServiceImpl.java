package com.getprepared.service.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.UserDao;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.ResultService;
import com.getprepared.service.UserService;
import com.getprepared.util.PasswordEncoder;

import java.util.List;

/**
 * Created by koval on 14.01.2017.
 */
@Bean("userService")
public class UserServiceImpl extends AbstractService implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private ResultService resultService;

    @Inject
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() { }

    @Override
    public User findById(final Long id) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findById(id);
            final List<Result> userResults = resultService.findByUserId(user.getId());
            user.setResults(userResults);
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

            final List<Result> userResults = resultService.findByUserId(user.getId());
            user.setResults(userResults);
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
            final String errorMsg = String.format("%s with this password %s does not exist", user.getRole(), password);
            throw new EntityNotFoundException(errorMsg);
        }
    }

    @Override
    public void signUp(final User user) throws EntityExistsException {
        try {
            transactionManager.begin();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }
    }
}
