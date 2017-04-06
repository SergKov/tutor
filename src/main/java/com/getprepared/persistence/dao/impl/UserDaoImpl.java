package com.getprepared.persistence.dao.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.context.Registry;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.persistence.dao.UserDao;
import com.getprepared.persistence.database.template.JdbcTemplate;
import com.getprepared.persistence.database.template.RowMapper;
import com.getprepared.persistence.domain.Role;
import com.getprepared.persistence.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.getprepared.context.Registry.*;
import static com.getprepared.context.constant.ServerConstant.PROPERTY_UTILS;
import static com.getprepared.persistence.constant.PropertyConstant.FILES_NAME;
import static com.getprepared.persistence.constant.PropertyConstant.KEY;
import static com.getprepared.persistence.domain.Entity.ID_KEY;
import static com.getprepared.persistence.domain.User.*;

/**
 * Created by koval on 06.01.2017.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Properties prop = getApplicationContext().getBean(PROPERTY_UTILS, PropertyUtils.class)
            .getProperty(FILES_NAME.USER);

    @Override
    public void save(final User user) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEY.SAVE), user,
                ps -> {
                    ps.setString(1, user.getRole().name());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getName());
                    ps.setString(5, user.getSurname());
                });
    }

    @Override
    public void updateStudentPassword(final String password) {
        jdbcTemplate.update(prop.getProperty(KEY.UPDATE_STUDENT_PASSWORD), ps -> {
            ps.setString(1, password);
        });
    }

    @Override
    public void updateTutorPassword(final String password) {
        jdbcTemplate.update(prop.getProperty(KEY.UPDATE_TUTOR_PASSWORD), ps -> {
            ps.setString(1, password);
        });
    }

    @Override
    public User findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEY.FIND_BY_ID), rs -> rs.setLong(1, id),
                new UserMapper());
    }

    @Override
    public User findByStudentEmail(final String email) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEY.FIND_BY_STUDENT_EMAIL), rs -> rs.setString(1, email),
                new UserMapper());
    }

    @Override
    public User findByTutorEmail(String email) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEY.FIND_BY_TUTOR_EMAIL), rs -> rs.setString(1, email),
                new UserMapper());
    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final Role role = Role.valueOf(rs.getString(ROLE_KEY));
            final String email = rs.getString(EMAIL_KEY);
            final String password = rs.getString(PASSWORD_KEY);
            final String name = rs.getString(NAME_KEY);
            final String surname = rs.getString(SURNAME_KEY);
            return fillUser(id, role, email, password, name, surname);
        }

        private User fillUser(final Long id, final Role role, final String email, final String password,
                              final String name, final String surname) {
            final User user = new User();
            user.setId(id);
            user.setRole(role);
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            return user;
        }
    }
}
