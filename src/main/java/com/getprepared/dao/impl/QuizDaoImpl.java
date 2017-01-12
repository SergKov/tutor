package com.getprepared.dao.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import com.getprepared.utils.PropertyUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Quiz.NAME_KEY;
import static com.getprepared.domain.Quiz.TIME_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuizDaoImpl extends AbstractDao<Quiz> implements QuizDao {

    private static final Logger LOG = Logger.getLogger(QuizDaoImpl.class);

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.QUIZ);

    public QuizDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Quiz quiz) {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), quiz,
                ps -> {
                    ps.setString(1, quiz.getName());
                    ps.setTime(2, Time.valueOf(quiz.getTime()));
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {

        final Optional<Quiz> optional = jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_ID),
                ps -> ps.setLong(1, id), new QuizMapper());

        return optional.get();
    }

    @Override
    public List<Quiz> findByUserId(final Long id) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_BY_USER_ID), ps -> ps.setLong(1, id),
                new QuizMapper());
    }

    @Override
    public List<Quiz> findByUserEmail(final String email) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_BY_EMAIL), ps -> ps.setString(1, email),
                new QuizMapper());
    }

    @Override
    public void connect(Long userId, Long quizId) {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.CONNECT),
                ps -> {
                    ps.setLong(1, userId);
                    ps.setLong(2, quizId);
                });
    }

    @Override
    public List<Quiz> findAll() {
        //TODO
        return null;
    }

    @Override
    public void updateTime(final LocalTime time) {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.UPDATE_TIME), ps -> ps.setTime(1, Time.valueOf(time)));
    }

    @Override
    public void remove(final Long id) {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.REMOVE_BY_ID), ps -> ps.setLong(1, id));
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
