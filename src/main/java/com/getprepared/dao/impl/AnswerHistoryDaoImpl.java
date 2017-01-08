package com.getprepared.dao.impl;

import com.getprepared.dao.AnswerHistoryDao;
import com.getprepared.domain.AnswerHistory;
import com.getprepared.domain.QuestionHistory;
import com.getprepared.exception.DataAccessException;
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
import static com.getprepared.domain.AnswerHistory.QUESTION_ID_KEY;
import static com.getprepared.domain.AnswerHistory.TEXT_KEY;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class AnswerHistoryDaoImpl extends AbstractDao<AnswerHistory> implements AnswerHistoryDao {

    private static final Logger LOG = Logger.getLogger(AnswerHistoryDaoImpl.class);

    private final ConnectionProvider provider;

    public AnswerHistoryDaoImpl() {
        provider = new TransactionalConnectionProvider();
    }

    @Override
    public void save(AnswerHistory answer) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.CHOSEN_ANSWER, KEYS.SAVE))) {

            preparedStatement.setLong(1, answer.getQuestion().getId());
            preparedStatement.setString(2, answer.getText());

            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save chosen AnswerHistory", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<AnswerHistory> findByQuestionId(Long questionId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                        .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.CHOSEN_ANSWER,
                                KEYS.FIND_BY_QUESTION_ID))) {

            preparedStatement.setLong(1, questionId);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                final List<AnswerHistory> answers = new ArrayList<>();

                if (rs.next()) {
                    answers.add(getEntity(rs));
                }

                return answers;
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find AnswerHistory by question id %d", questionId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected AnswerHistory getEntity(ResultSet rs) {
        try {
            final Long id = rs.getLong(ID_KEY);
            final QuestionHistory question = new QuestionHistory();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            return new AnswerHistory(id, question, text);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from AnswerHistory ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
