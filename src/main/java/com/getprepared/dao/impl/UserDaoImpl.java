package com.getprepared.dao.impl;

import com.getprepared.dao.UserDao;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import com.getprepared.utils.PropertyUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.User.*;

/**
 * Created by koval on 06.01.2017.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.USER);

    public UserDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final User user) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), user,
                ps -> {
                    ps.setString(1, user.getRole().name());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getPassword());
                    ps.setString(4, user.getName());
                    ps.setString(5, user.getSurname());
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public User findByCredentials(final String email, final String password) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_CREDENTIALS),
                rs -> {
                    rs.setString(1, email);
                    rs.setString(2, password);
                }, new UserMapper());
    }

    @Override
    public User findByEmail(final String email) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_CREDENTIALS), rs -> rs.setString(1, email),
                new UserMapper());
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
    public void updateCredentials(final String email, final String password) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.UPDATE_CREDENTIALS),
                rs -> {
                    rs.setString(1, email);
                    rs.setString(2, password);
                });
    }

    @Override
    public void remove(Long userId, Long quizId) {
        //TODO
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
