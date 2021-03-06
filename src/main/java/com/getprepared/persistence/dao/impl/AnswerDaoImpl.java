package com.getprepared.persistence.dao.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.persistence.annotation.Repository;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.database.template.BatchPreparedStatementSetter;
import com.getprepared.persistence.database.template.JdbcTemplate;
import com.getprepared.persistence.database.template.RowMapper;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.context.constant.ServerConstant.PROPERTY_UTILS;
import static com.getprepared.persistence.constant.PropertyConstant.FILES_NAME;
import static com.getprepared.persistence.constant.PropertyConstant.KEY;
import static com.getprepared.persistence.domain.Answer.*;
import static com.getprepared.persistence.domain.Entity.ID_KEY;

/**
 * Created by koval on 05.01.2017.
 */
@Repository("answerDao")
public class AnswerDaoImpl implements AnswerDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Properties prop = getApplicationContext().getBean(PROPERTY_UTILS, PropertyUtils.class)
            .getProperty(FILES_NAME.ANSWER);

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEY.SAVE), answer,
                ps -> {
                    ps.setLong(1, answer.getQuestion().getId());
                    ps.setString(2, answer.getText());
                    ps.setString(3, answer.getType().name());
                });
    }

    @Override
    public void saveBatch(final List<Answer> answers) throws EntityExistsException {
        jdbcTemplate.batchSave(prop.getProperty(KEY.SAVE), answers, new BatchPreparedStatementSetter() {
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
    public void updateBatch(final List<Answer> answers) {
        jdbcTemplate.batchUpdate(prop.getProperty(KEY.UPDATE), new BatchPreparedStatementSetter() {
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
        return jdbcTemplate.singleQuery(String.format(prop.getProperty(KEY.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new AnswerMapper());
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_BY_ID), QUESTION_ID_KEY),
                ps -> ps.setLong(1, questionId), new AnswerMapper());
    }

    @Override
    public List<Answer> findByQuestionIdInRandomOrder(final Long questionId) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEY.FIND_BY_QUESTION_ID_RANDOM),
                ps -> ps.setLong(1, questionId), new AnswerMapper());
    }

    private static class AnswerMapper implements RowMapper<Answer> {

        @Override
        public Answer mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final Question question = new Question();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            final Type type = Type.valueOf(rs.getString(TYPE_KEY));
            return fillAnswer(id, question, text, type);
        }

        private Answer fillAnswer(final Long id, final Question question, final String text, final Type type) {
            final Answer answer = new Answer();
            answer.setId(id);
            answer.setQuestion(question);
            answer.setText(text);
            answer.setType(type);
            return answer;
        }
    }
}
