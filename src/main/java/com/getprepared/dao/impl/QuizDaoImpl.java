package com.getprepared.dao.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Quiz.NAME_KEY;
import static com.getprepared.domain.Quiz.TIME_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuizDaoImpl extends AbstractDao<Quiz> implements QuizDao {

    private static final Logger LOG = Logger.getLogger(QuizDaoImpl.class);

    public QuizDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Quiz quiz) {
        //TODO
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        //TODO throw exception
        return null;
    }

    @Override
    public List<Quiz> findByUserId(final Long id) {
        //TODO add pagination
        return null;
    }

    @Override
    public List<Quiz> findByUserEmail(final String email) {
        //TODO add pagination
        return null;
    }

    @Override
    public List<Quiz> findAll() {
        //TODO add pagination
        return null;
    }

    @Override
    public void updateTime(final LocalTime time) {
        //TODO
    }

    @Override
    public void removeById(final Long id) {
        //TODO throw exception
    }

    private static class QuizMapper implements RowMapper<Quiz> {

        @Override
        public Quiz mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final String name = rs.getString(NAME_KEY);
            final LocalTime time = rs.getTime(TIME_KEY).toLocalTime();
            return new Quiz(id, name, time);
        }
    }
}
