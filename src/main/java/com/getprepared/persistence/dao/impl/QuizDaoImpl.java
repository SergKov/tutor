package com.getprepared.persistence.dao.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.persistence.dao.QuizDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.database.template.JdbcTemplate;
import com.getprepared.persistence.database.template.RowMapper;
import com.getprepared.persistence.domain.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.core.constant.PropertyConstant.FILES_NAME;
import static com.getprepared.core.constant.PropertyConstant.KEY;
import static com.getprepared.persistence.domain.Entity.ID_KEY;
import static com.getprepared.persistence.domain.Quiz.NAME_KEY;

/**
 * Created by koval on 06.01.2017.
 */
@Component("quizDao")
public class QuizDaoImpl implements QuizDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Properties prop = PropertyUtils.getProperty(FILES_NAME.QUIZ);

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEY.SAVE), quiz, ps -> {
            ps.setString(1, quiz.getName());
            ps.setLong(2, quiz.getUser().getId());
        });
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEY.FIND_BY_ID), ps -> ps.setLong(1, id),
                new QuizMapper());
    }

    @Override
    public List<Quiz> findAllByTutorId(final Long id, final PageableData page) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_ALL_BY_TUTOR_ID), page.getLimit(),
                page.getOffset()), ps -> ps.setLong(1, id), new QuizMapper());
    }

    @Override
    public List<Quiz> findAllCreated(final PageableData page) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_ALL_CREATED), page.getLimit(),
                page.getOffset()), new QuizMapper());
    }

    @Override
    public Long countFoundRows() {
        return jdbcTemplate.countFoundRows();
    }

    @Override
    public void update(final String name, final Long id) throws EntityExistsException {
        jdbcTemplate.update(prop.getProperty(KEY.UPDATE), ps -> {
            ps.setString(1, name);
            ps.setLong(2, id);
        });
    }

    @Override
    public void activeQuiz(final Long id) {
        jdbcTemplate.update(prop.getProperty(KEY.ACTIVE_QUIZ), ps -> ps.setLong(1, id));
    }

    @Override
    public void remove(final Long id) {
        jdbcTemplate.remove(prop.getProperty(KEY.REMOVE_BY_ID), ps -> ps.setLong(1, id));
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
