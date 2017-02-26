package com.getprepared.dao.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.AnswerDao;
import com.getprepared.database.template.JdbcTemplate;
import com.getprepared.database.template.BatchPreparedStatementSetter;
import com.getprepared.database.template.RowMapper;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.util.impl.PropertyUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Answer.*;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 05.01.2017.
 */
@Bean("answerDao")
public class AnswerDaoImpl implements AnswerDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.ANSWER);

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), answer,
                ps -> {
                    ps.setLong(1, answer.getQuestion().getId());
                    ps.setString(2, answer.getText());
                    ps.setString(3, answer.getType().name());
                });
    }

    @Override
    public void saveBatch(final List<Answer> answers) throws EntityExistsException {
        jdbcTemplate.batchUpdate(prop.getProperty(KEYS.SAVE), answers, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                final Answer answer = answers.get(i);
                ps.setLong(1, answer.getQuestion().getId());
                ps.setString(2, answer.getText());
                ps.setString(3, answer.getType().name());
            }

            @Override
            public int getBatchSize() {
                return answers.size();
            }
        });
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new AnswerMapper());
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), QUESTION_ID_KEY),
                ps -> ps.setLong(1, questionId), new AnswerMapper());
    }

    private static class AnswerMapper implements RowMapper<Answer> {

        @Override
        public Answer mapRow(final ResultSet rs) throws SQLException{
            final Long id = rs.getLong(ID_KEY);
            final Question question = new Question();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            final AnswerType type = AnswerType.valueOf(rs.getString(TYPE_KEY));
            return fillAnswer(id, question, text, type);
        }

        public Answer fillAnswer(final Long id, final Question question, final String text, final AnswerType type) {
            final Answer answer = new Answer();
            answer.setId(id);
            answer.setQuestion(question);
            answer.setText(text);
            answer.setType(type);
            return answer;
        }
    }
}
