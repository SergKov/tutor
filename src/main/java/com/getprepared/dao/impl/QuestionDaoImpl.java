package com.getprepared.dao.impl;

import com.getprepared.constant.PropertyConstants.FILES_NAMES;
import com.getprepared.dao.QuestionDao;
import com.getprepared.database.template.JdbcTemplate;
import com.getprepared.database.template.function.RowMapper;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.utils.impl.PropertyUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Question.QUIZ_ID_KEY;
import static com.getprepared.domain.Question.TEXT_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.QUESTION);

    public QuestionDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Question question) throws EntityExistsException {
        getJdbcTemplate().executeUpdate(prop.getProperty(KEYS.SAVE), question,
                ps -> {
                    ps.setLong(1, question.getQuiz().getId());
                    ps.setString(2, question.getText());
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Question findById(final Long id) throws EntityNotFoundException {
        return getJdbcTemplate().singleQuery(
                String.format(prop.getProperty(KEYS.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new QuestionMapper());
    }

    @Override
    public List<Question> findByQuizId(final Long quizId) {
        return getJdbcTemplate().executeQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), QUIZ_ID_KEY),
                ps -> ps.setLong(1, quizId), new QuestionMapper());
    }

    @Override
    public void removeById(final Long id)  {
        getJdbcTemplate().remove(prop.getProperty(KEYS.REMOVE_BY_ID), ps -> ps.setLong(1, id));
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

        public Question fillQuestion(final Long id, final Quiz quiz, final String text) {
            final Question question = new Question();
            question.setId(id);
            question.setQuiz(quiz);
            question.setText(text);
            return question;
        }
    }
}
