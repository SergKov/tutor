package com.getprepared.persistence.dao;

import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.domain.User;

/**
 * Created by koval on 03.01.2017.
 */
public interface UserDao {

    void save(User user) throws EntityExistsException;

    void updateStudentPassword(String password);

    void updateTutorPassword(String password);

    User findById(Long id) throws EntityNotFoundException;

    User findByStudentEmail(String email) throws EntityNotFoundException;

    User findByTutorEmail(String email) throws EntityNotFoundException;
}
