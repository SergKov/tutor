package com.getprepared.dao.impl;

import com.getprepared.dao.QuestionHistoryDao;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.QuestionHistory;
import com.getprepared.domain.Result;
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
import static com.getprepared.domain.QuestionHistory.*;

/**
 * Created by koval on 06.01.2017.
 */
public class QuestionHistoryDaoImpl extends AbstractDao<QuestionHistory> implements QuestionHistoryDao {

    private static final Logger LOG = Logger.getLogger(QuestionHistoryDao.class);

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.QUESTION_HISTORY);

    public QuestionHistoryDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final QuestionHistory question) {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), question,
                ps -> {
                    ps.setLong(1, question.getResult().getId());
                    ps.setString(2, question.getText());
                    ps.setString(3, question.getType().name());
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public List<QuestionHistory> findByResultId(final Long resultId) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_BY_RESULT_ID), ps -> ps.setLong(1, resultId),
                new QuestionHistoryMapper());
    }

    private static class QuestionHistoryMapper implements RowMapper<QuestionHistory> {

        @Override
        public QuestionHistory mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final Result result = new Result();
            result.setId(rs.getLong(RESULT_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            final AnswerType answerType = AnswerType.valueOf(rs.getString(TYPE_KEY));
            return new QuestionHistory(id, result, text, answerType);
        }
    }
}
