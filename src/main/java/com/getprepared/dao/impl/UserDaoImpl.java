package com.getprepared.dao.impl;

import com.getprepared.dao.UserDao;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.User.*;

/**
 * Created by koval on 06.01.2017.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final User user) {
        //TODO
    }

    @Override
    public User findByCredentials(final String email, final String password) throws EntityNotFoundException {
        //TODO throw exception
        return null;
    }

    @Override
    public User findByEmail(final String email) throws EntityNotFoundException {
        //TODO throw exception
        return null;
    }

    @Override
    public List<User> findAllByQuizId(final Long quizId) {
        //TODO add pagination, throw excepton
        return null;
    }

    @Override
    public List<User> findAll() {
        //TODO add pagination
        return null;
    }

    @Override
    public void updateCredentials(final User user) {
        //TODO throw exception
    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final Role role = Role.valueOf(rs.getString(ROLE_KEY));
            final String email = rs.getString(EMAIL_KEY);
            final String password = rs.getString(PASSWORD_KEY);
            final String name = rs.getString(NAME_KEY);
            final String surname = rs.getString(SURNAME_KEY);
            return new User(id, role, email, password, name, surname);
        }
    }
}
