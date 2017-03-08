package com.getprepared.dao.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.QuizDao;
import com.getprepared.database.template.JdbcTemplate;
import com.getprepared.database.template.RowMapper;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.util.impl.PropertyUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Quiz.NAME_KEY;

/**
 * Created by koval on 06.01.2017.
 */
@Component("quizDao")
public class QuizDaoImpl implements QuizDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.QUIZ);

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), quiz, ps -> ps.setString(1, quiz.getName()));
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_ID), ps -> ps.setLong(1, id),
                new QuizMapper());
    }

    @Override
    public List<Quiz> findAll() {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_ALL), new QuizMapper());
    }

    @Override
    public void remove(final Long id) {
        jdbcTemplate.remove(prop.getProperty(KEYS.REMOVE_BY_ID), ps -> ps.setLong(1, id));
    }

    private static class QuizMapper implements RowMapper<Quiz> {

        @Override
        public Quiz mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final String name = rs.getString(NAME_KEY);
            return fillQuiz(id, name);
        }

        private Quiz fillQuiz(final Long id, final String name) {
            final Quiz quiz = new Quiz();
            quiz.setId(id);
            quiz.setName(name);
            return quiz;
        }
    }
}
