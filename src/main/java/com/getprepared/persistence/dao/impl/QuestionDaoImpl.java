package com.getprepared.persistence.dao.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.persistence.annotation.Repository;
import com.getprepared.persistence.constant.PropertyConstant.FILES_NAME;
import com.getprepared.persistence.dao.QuestionDao;
import com.getprepared.persistence.database.template.JdbcTemplate;
import com.getprepared.persistence.database.template.RowMapper;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.context.constant.ServerConstant.PROPERTY_UTILS;
import static com.getprepared.persistence.constant.PropertyConstant.KEY;
import static com.getprepared.persistence.domain.Entity.ID_KEY;
import static com.getprepared.persistence.domain.Question.QUIZ_ID_KEY;
import static com.getprepared.persistence.domain.Question.TEXT_KEY;

/**
 * Created by koval on 06.01.2017.
 */
@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Properties prop = getApplicationContext().getBean(PROPERTY_UTILS, PropertyUtils.class)
            .getProperty(FILES_NAME.QUESTION);

    @Override
    public void save(final Question question) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEY.SAVE), question,
                ps -> {
                    ps.setLong(1, question.getQuiz().getId());
                    ps.setString(2, question.getText());
                });
    }

    @Override
    public Question findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(
                String.format(prop.getProperty(KEY.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new QuestionMapper());
    }

    @Override
    public List<Question> findByQuizId(final Long quizId) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_BY_ID), QUIZ_ID_KEY),
                ps -> ps.setLong(1, quizId), new QuestionMapper());
    }

    @Override
    public List<Question> findByQuizIdInRandomOrder(final Long quizId) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_BY_QUIZ_ID_RANDOM), QUIZ_ID_KEY),
                ps -> ps.setLong(1, quizId), new QuestionMapper());
    }

    @Override
    public void update(final String text, final Long id) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEY.UPDATE), ps -> {
            ps.setString(1, text);
            ps.setLong(2, id);
        });
    }

    @Override
    public void remove(final Long id) {
        jdbcTemplate.remove(prop.getProperty(KEY.REMOVE_BY_ID), ps -> ps.setLong(1, id));
    }

    private static class QuestionMapper implements RowMapper<Question> {

        @Override
        public Question mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final Quiz quiz = new Quiz();
            quiz.setId(rs.getLong(QUIZ_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            return fillQuestion(id, quiz, text);
        }

        private Question fillQuestion(final Long id, final Quiz quiz, final String text) {
            final Question question = new Question();
            question.setId(id);
            question.setQuiz(quiz);
            question.setText(text);
            return question;
        }
    }
}
