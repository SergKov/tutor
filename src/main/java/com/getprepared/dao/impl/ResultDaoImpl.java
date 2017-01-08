package com.getprepared.dao.impl;

import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.DataAccessException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.TransactionalConnectionProvider;
import com.getprepared.utils.PropertyUtils;
import com.sun.xml.internal.ws.server.ServerRtException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Result.*;
import static com.getprepared.domain.Result.MARK_KEY;
import static com.getprepared.domain.Result.QUIZ_NAME_KEY;
import static com.getprepared.domain.Result.USER_ID_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class ResultDaoImpl extends AbstractDao<Result> implements ResultDao {

    private static final Logger LOG = Logger.getLogger(ResultDaoImpl.class);

    private ConnectionProvider provider;

    public ResultDaoImpl() {
        provider = new TransactionalConnectionProvider();
    }

    @Override
    public void save(final Result result) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.RESULT, KEYS.SAVE))) {

            preparedStatement.setLong(1, result.getUser().getId());
            preparedStatement.setByte(2, result.getMark());
            preparedStatement.setString(3, result.getQuizName());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save result", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public Result findById(final Long id) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.RESULT, KEYS.FIND_BY_ID))) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getEntity(rs);
                } else {
                    throw new EntityNotFoundException(String.format("Quiz with id %d is not found", id));
                }
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find result by id %d", id), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Result> findByUserEmail(final String email) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.RESULT, KEYS.FIND_BY_EMAIL))) {

            preparedStatement.setString(1, email);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                final List<Result> results = new ArrayList<>();

                if (rs.next()) {
                    results.add(getEntity(rs));
                }

                return results;
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find results by user email %s", email), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected Result getEntity(final ResultSet rs) {

        try {
            final Long id = rs.getLong(ID_KEY);
            final User user = new User();
            user.setId(rs.getLong(USER_ID_KEY));
            final Byte mark = rs.getByte(MARK_KEY);
            final String quizName = rs.getString(QUIZ_NAME_KEY);
            final LocalDateTime dateTime = rs.getTimestamp(CREATION_DATETIME_KEY).toLocalDateTime();
            return new Result(id, user, mark, quizName, dateTime);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from Result ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
