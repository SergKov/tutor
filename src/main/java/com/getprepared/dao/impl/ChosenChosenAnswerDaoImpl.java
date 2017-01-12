package com.getprepared.dao.impl;

import com.getprepared.constant.PropertyConstants.FILES_NAMES;
import com.getprepared.dao.ChosenAnswerDao;
import com.getprepared.domain.ChosenAnswer;
import com.getprepared.domain.QuestionHistory;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import com.getprepared.utils.PropertyUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.ChosenAnswer.QUESTION_ID_KEY;
import static com.getprepared.domain.ChosenAnswer.TEXT_KEY;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class ChosenChosenAnswerDaoImpl extends AbstractDao<ChosenAnswer> implements ChosenAnswerDao {

    private static final Logger LOG = Logger.getLogger(ChosenChosenAnswerDaoImpl.class);

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.CHOSEN_ANSWER);

    public ChosenChosenAnswerDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(ChosenAnswer answer) {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), answer,
                ps -> {
                    ps.setLong(1, answer.getQuestion().getId());
                    ps.setString(2, answer.getText());
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public List<ChosenAnswer> findByQuestionId(Long questionId) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_BY_QUESTION_ID), ps -> ps.setLong(1, questionId),
                                                                                        new ChosenAnswerMapper());
    }

    private static class ChosenAnswerMapper implements RowMapper<ChosenAnswer> {

        @Override
        public ChosenAnswer mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final QuestionHistory question = new QuestionHistory();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            return new ChosenAnswer(id, question, text);
        }
    }
}
