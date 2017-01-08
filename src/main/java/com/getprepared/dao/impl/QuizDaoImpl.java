package com.getprepared.dao.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.DataAccessException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.TransactionalConnectionProvider;
import com.getprepared.utils.PropertyUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Quiz.NAME_KEY;
import static com.getprepared.domain.Quiz.TIME_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuizDaoImpl extends AbstractDao<Quiz> implements QuizDao {

    private static final Logger LOG = Logger.getLogger(QuizDaoImpl.class);

    private ConnectionProvider provider;

    public QuizDaoImpl() {
        provider = new TransactionalConnectionProvider();
    }

    @Override
    public void save(final Quiz quiz) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUIZ, KEYS.SAVE))) {

            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setTime(2, Time.valueOf(quiz.getTime()));
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save quiz", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUIZ, KEYS.FIND_BY_ID))) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getEntity(rs);
                } else {
                    throw new EntityNotFoundException(String.format("Quiz with id %d is not found", id));
                }
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find quiz by id %d", id), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Quiz> findByUserEmail(final String email) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUIZ, KEYS.FIND_BY_EMAIL))) {

            preparedStatement.setString(1, email);

            return getQuizzes(preparedStatement);

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find quizzes by user email %s", email), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Quiz> findAll() {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUIZ, KEYS.FIND_ALL))) {

            return getQuizzes(preparedStatement);

        } catch (final SQLException e) {
            LOG.error("Failed to find all quizzes", e);
            throw new DataAccessException(e);
        }
    }

    private List<Quiz> getQuizzes(PreparedStatement preparedStatement) throws SQLException {

        try (ResultSet rs = preparedStatement.executeQuery()) {

            final List<Quiz> quizzes = new ArrayList<>();

            if (rs.next()) {
                quizzes.add(getEntity(rs));
            }

            return quizzes;
        }
    }

    @Override
    public void updateTime(final LocalTime time) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUIZ, KEYS.UPDATE_TIME))) {

            preparedStatement.setTime(1, Time.valueOf(time));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            LOG.error("Failed to updateCredentials time for Quiz", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public void removeById(final Long id) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUIZ, KEYS.REMOVE_BY_ID))) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to remove quiz by id %d", id), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected Quiz getEntity(ResultSet rs) {
        try {
            final Long id = rs.getLong(ID_KEY);
            final String name = rs.getString(NAME_KEY);
            final LocalTime time = rs.getTime(TIME_KEY).toLocalTime();
            return new Quiz(id, name, time);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from Quiz ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
