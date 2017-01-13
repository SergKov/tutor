package com.getprepared.dao.impl;

import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import com.getprepared.utils.PropertyUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Answer.*;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 05.01.2017.
 */
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.ANSWER);

    public AnswerDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), answer,
                ps -> {
                    ps.setLong(1, answer.getQuestion().getId());
                    ps.setString(2, answer.getText());
                    ps.setString(3, answer.getType().name());
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_ID),
                ps -> ps.setLong(1, id), new AnswerMapper());
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_BY_QUESTION_ID), ps -> ps.setLong(1, questionId),
                                                                                                    new AnswerMapper());
    }

    @Override
    public void removeByQuestionId(final Long questionId) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.REMOVE_BY_QUESTION_ID), ps -> ps.setLong(1, questionId));
    }

    private static class AnswerMapper implements RowMapper<Answer> {

        @Override
        public Answer mapRow(ResultSet rs) throws SQLException{
            final Long id = rs.getLong(ID_KEY);
            final Question question = new Question();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            final AnswerType type = AnswerType.valueOf(rs.getString(TYPE_KEY));
            return new Answer(id, question, text, type);
        }
    }
}
