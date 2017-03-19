package com.getprepared.dao.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.UserDao;
import com.getprepared.database.template.JdbcTemplate;
import com.getprepared.database.template.RowMapper;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.util.PropertyUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.User.*;
import static com.getprepared.util.PropertyUtils.*;

/**
 * Created by koval on 06.01.2017.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {

    private static final Properties prop = initProp(FILES_NAMES.USER);

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(final User user) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), user,
                ps -> {
                    ps.setString(1, user.getRole().name());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getName());
                    ps.setString(5, user.getSurname());
                });
    }

    @Override
    public User findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_ID), rs -> rs.setLong(1, id),
                new UserMapper());
    }

    @Override
    public User findByStudentEmail(final String email) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_STUDENT_EMAIL), rs -> rs.setString(1, email),
                new UserMapper());
    }

    @Override
    public User findByTutorEmail(String email) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_TUTOR_EMAIL), rs -> rs.setString(1, email),
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
