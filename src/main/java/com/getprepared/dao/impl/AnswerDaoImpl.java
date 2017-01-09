package com.getprepared.dao.impl;

import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
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
import static com.getprepared.domain.Answer.*;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 05.01.2017.
 */
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    private static final Logger LOG = Logger.getLogger(AnswerDaoImpl.class);


    public AnswerDaoImpl() { }

    @Override
    public void save(final Answer answer) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(getPropertyUtils().getQuery(FILES_NAMES.ANSWER, KEYS.SAVE))) {

            preparedStatement.setLong(1, answer.getQuestion().getId());
            preparedStatement.setString(2, answer.getText());
            preparedStatement.setString(3, answer.getType().name());
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save answer", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(getPropertyUtils()
                        .getQuery(FILES_NAMES.ANSWER, KEYS.FIND_BY_ID), ID_KEY))) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getEntity(rs);
                } else {
                    throw new EntityNotFoundException(String.format("Answer with id %d is not found", id));
                }
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find answer by id %d", id), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(getPropertyUtils().getQuery(FILES_NAMES.ANSWER, KEYS.FIND_BY_ID),
                        QUESTION_ID_KEY))) {

            preparedStatement.setLong(1, questionId);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                final List<Answer> answers = new ArrayList<>();

                if (rs.next()) {
                    answers.add(getEntity(rs));
                }

                return answers;
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find answers by id %d", questionId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public void removeByQuestionId(final Long questionId) { //TODO ???

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(String.format(getPropertyUtils().getQuery(FILES_NAMES.ANSWER, KEYS.REMOVE_BY_ID),
                        QUESTION_ID_KEY))) {

            preparedStatement.setLong(1, questionId);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to removeById answers by question id %d", questionId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected Answer getEntity(final ResultSet rs) {
        try {
            final Long id = rs.getLong(ID_KEY);
            final Question question = new Question();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            final AnswerType type = AnswerType.valueOf(rs.getString(TYPE_KEY));
            return new Answer(id, question, text, type);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from Answer ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
