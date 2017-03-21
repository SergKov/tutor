package com.getprepared.core.service.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.persistence.dao.UserDao;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.service.UserService;
import com.getprepared.core.util.PasswordEncoder;

import java.util.List;

/**
 * Created by koval on 14.01.2017.
 */
@Component("userService")
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
            transactionManager.rollback();
            user.setResults(userResults);
            return user;
        } catch (final EntityNotFoundException e) {
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
            transactionManager.rollback();
            user.setResults(userResults);
            return user;
        } catch (final EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public User signInTutor(final String email, final String password) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final User user = userDao.findByTutorEmail(email);
            checkPassword(password, user);
            transactionManager.rollback();
            return user;
        } catch (final EntityNotFoundException e) {
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
        } catch (final EntityExistsException e) {
            throw e;
        }
    }
}
