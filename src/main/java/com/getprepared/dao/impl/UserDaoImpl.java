package com.getprepared.dao.impl;

import com.getprepared.dao.UserDao;
import com.getprepared.domain.Role;
import com.getprepared.domain.User;
import com.getprepared.exception.DataAccessException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.TransactionalConnectionProvider;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.User.*;

/**
 * Created by koval on 06.01.2017.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() { }

    @Override
    public void save(final User user) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.USER, KEYS.SAVE))) {

            preparedStatement.setString(1, user.getRole().name());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save user", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public User findByCredentials(final String email, final String password) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.USER, KEYS.FIND_BY_CREDENTIALS))) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getEntity(rs);
                } else {
                    throw new EntityNotFoundException(String.format("User with email %s and password %s is not found",
                            email, password));
                }
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find user with email %s and password %s", email, password), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public User findByEmail(final String email) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.RESULT, KEYS.FIND_BY_EMAIL))) {

            preparedStatement.setString(1, email);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getEntity(rs);
                } else {
                    throw new EntityNotFoundException(String.format("User with email %s is not found", email));
                }
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find user with email %s", email), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<User> findAllByQuizId(final Long quizId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.USER, KEYS.FIND_BY_QUIZ_ID))) {

            preparedStatement.setLong(1, quizId);
            return getUsers(preparedStatement);
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to findAll by quizId %d", quizId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<User> findAll() {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.USER, KEYS.FIND_ALL))) {

            return getUsers(preparedStatement);
        } catch (final SQLException e) {
            LOG.error("Failed to findAll", e);
            throw new DataAccessException(e);
        }
    }

    private List<User> getUsers(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet rs = preparedStatement.executeQuery()) {

            final List<User> users = new ArrayList<>();
            if (rs.next()) {
                users.add(getEntity(rs));
            }

            return users;
        }
    }

    @Override
    public void updateCredentials(final User user) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.USER, KEYS.UPDATE_CREDENTIALS))) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            LOG.error("Failed to updateCredentials for User", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected User getEntity(final ResultSet rs) {

        try {
            final Long id = rs.getLong(ID_KEY);
            final Role role = Role.valueOf(rs.getString(ROLE_KEY));
            final String email = rs.getString(EMAIL_KEY);
            final String password = rs.getString(PASSWORD_KEY);
            final String name = rs.getString(NAME_KEY);
            final String surname = rs.getString(SURNAME_KEY);
            return new User(id, role, email, password, name, surname);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from User ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
