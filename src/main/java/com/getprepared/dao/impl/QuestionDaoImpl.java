package com.getprepared.dao.impl;

import com.getprepared.dao.QuestionDao;
import com.getprepared.domain.Question;
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
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Question.QUIZ_ID_KEY;
import static com.getprepared.domain.Question.TEXT_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private static final Logger LOG = Logger.getLogger(QuestionDaoImpl.class);

    private ConnectionProvider provider;

    public QuestionDaoImpl() {
        provider = new TransactionalConnectionProvider();
    }

    @Override
    public void save(final Question question) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.SAVE))) {

            preparedStatement.setLong(1, question.getQuiz().getId());
            preparedStatement.setString(2, question.getText());
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save question", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public Question findById(final Long id) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.FIND_BY_ID), ID_KEY))) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getEntity(rs);
                } else {
                    throw new EntityNotFoundException(String.format("Question with id %d is not found", id));
                }
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find question by id %d", id), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Question> createNewQuiz(final Long quizId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.CREATE_NEW_QUIZ))) {

            preparedStatement.setLong(1, quizId);
            return getQuestions(preparedStatement);
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to create quiz by quiz id %d", quizId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Question> findByQuizId(final Long quizId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.FIND_BY_ID),
                        Question.QUIZ_ID_KEY))) {

            preparedStatement.setLong(1, quizId);
            return getQuestions(preparedStatement);
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find questions by id %d", quizId), e);
            throw new DataAccessException(e);
        }
    }

    private List<Question> getQuestions(PreparedStatement preparedStatement) throws SQLException {

        try (ResultSet rs = preparedStatement.executeQuery()) {

            final List<Question> quiz = new ArrayList<>();

            if (rs.next()) {
                quiz.add(getEntity(rs));
            }

            return quiz;
        }
    }

    @Override
    public void update(final Question question) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.UPDATE))) {

            preparedStatement.setString(1, question.getText());
            preparedStatement.setLong(2, question.getId());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            LOG.error("Failed to updateCredentials question", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public void removeById(final Long id) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.REMOVE_BY_ID),
                        ID_KEY))) {

            preparedStatement.setLong(1, id);
            final int removedId = preparedStatement.executeUpdate();

            if (removedId == 0) {
                throw new EntityNotFoundException(String.format("Question with id %d is not found", id));
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to removeById question by id %d", id), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public void removeByQuizId(final Long quizId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(PropertyUtils.getQuery(FILES_NAMES.QUESTION, KEYS.REMOVE_BY_ID),
                        QUIZ_ID_KEY))) {

            preparedStatement.setLong(1, quizId);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to removeById questions by quiz id %d", quizId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected Question getEntity(ResultSet rs) {
        try {
            final Long id = rs.getLong(ID_KEY);
            final Quiz quiz = new Quiz();
            quiz.setId(rs.getLong(QUIZ_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            return new Question(id, quiz, text);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from Question ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
