package com.getprepared.dao.impl;

import com.getprepared.constant.PropertyConstants.FILES_NAMES;
import com.getprepared.constant.PropertyConstants.KEYS;
import com.getprepared.dao.QuestionHistoryDao;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.QuestionHistory;
import com.getprepared.domain.Result;
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

import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.QuestionHistory.*;

/**
 * Created by koval on 06.01.2017.
 */
public class QuestionHistoryDaoImpl extends AbstractDao<QuestionHistory> implements QuestionHistoryDao {

    private static final Logger LOG = Logger.getLogger(QuestionHistoryDao.class);

    private ConnectionProvider provider;

    public QuestionHistoryDaoImpl() {
        provider = new TransactionalConnectionProvider();
    }

    @Override
    public void save(final QuestionHistory question) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUESTION_HISTORY, KEYS.SAVE))) {

            preparedStatement.setLong(1, question.getResult().getId());
            preparedStatement.setString(2, question.getText());
            preparedStatement.setString(3, question.getType().name());
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            LOG.error("Failed to save question", e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<QuestionHistory> findByResultId(final Long resultId) {

        try (PreparedStatement preparedStatement = getConnection(provider)
                .prepareStatement(PropertyUtils.getQuery(FILES_NAMES.QUESTION_HISTORY, KEYS.FIND_BY_RESULT_ID))) {

            preparedStatement.setLong(1, resultId);

            final List<QuestionHistory> questionHistory = new ArrayList<>();

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    questionHistory.add(getEntity(rs));
                }
            }

            return questionHistory;

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to find question by id %d", resultId), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected QuestionHistory getEntity(ResultSet rs) {

        try {
            final Long id = rs.getLong(ID_KEY);
            final Result result = new Result();
            result.setId(rs.getLong(RESULT_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            final AnswerType answerType = AnswerType.valueOf(rs.getString(TYPE_KEY));
            return new QuestionHistory(id, result, text, answerType);
        } catch (final SQLException e) {
            LOG.error("Failed to retrieve information from QuestionHistory ResultSet", e);
            throw new DataAccessException(e);
        }
    }
}
