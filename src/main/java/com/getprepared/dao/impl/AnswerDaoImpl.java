package com.getprepared.dao.impl;

import com.getprepared.dao.AnswerDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.getprepared.domain.Answer.*;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 05.01.2017.
 */
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    private static final Logger LOG = Logger.getLogger(AnswerDaoImpl.class);

    public AnswerDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void save(final Answer answer) {
        //TODO
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        //TODO throw exception
        return null;
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        //TODO throw exception
        return null;
    }

    @Override
    public void removeByQuestionId(final Long questionId) {
        //TODO throw exception
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
