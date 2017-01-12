package com.getprepared.dao.impl;

import com.getprepared.dao.QuestionDao;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Question.QUIZ_ID_KEY;
import static com.getprepared.domain.Question.TEXT_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private static final Logger LOG = Logger.getLogger(QuestionDaoImpl.class);

    public QuestionDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Question question) {
        //TODO
    }

    @Override
    public Question findById(final Long id) throws EntityNotFoundException {
        //TODO
        return null;
    }

    @Override
    public List<Question> createNewQuiz(final Long quizId) {
        //TODO
        return null;
    }

    @Override
    public List<Question> findByQuizId(final Long quizId) {
        //TODO add pagination
        return null;
    }

    @Override
    public void removeById(final Long id) throws EntityNotFoundException {
        //TODO throw exception
    }

    @Override
    public void removeByQuizId(final Long quizId) {
        //TODO throw exception
    }

    private static class QuestionMapper implements RowMapper<Question> {

        @Override
        public Question mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final Quiz quiz = new Quiz();
            quiz.setId(rs.getLong(QUIZ_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            return new Question(id, quiz, text);
        }
    }
}
