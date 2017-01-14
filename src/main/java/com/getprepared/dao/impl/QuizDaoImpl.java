package com.getprepared.dao.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import com.getprepared.utils.PropertyUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Quiz.NAME_KEY;
import static com.getprepared.domain.Quiz.SPECIALITY_ID_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class QuizDaoImpl extends AbstractDao<Quiz> implements QuizDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.QUIZ);

    public QuizDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), quiz, ps -> ps.setString(1, quiz.getName()),
                PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(prop.getProperty(KEYS.FIND_BY_ID),
                ps -> ps.setLong(1, id), new QuizMapper());
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
    public void institute(Long userId, Long quizId) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.INSTITUTE),
                ps -> {
                    ps.setLong(1, userId);
                    ps.setLong(2, quizId);
                });
    }

    @Override
    public List<Quiz> findAllBySpecialityId(Long specialityId)  {
        return jdbcTemplate.executeQuery(prop.getProperty(KEYS.FIND_BY_SPECIALITY_ID),
                ps -> ps.setLong(1, specialityId), new QuizMapper());
    }

    @Override
    public void remove(final Long id) {
        jdbcTemplate.remove(prop.getProperty(KEYS.REMOVE_BY_ID), ps -> ps.setLong(1, id));
    }

    private static class QuizMapper implements RowMapper<Quiz> {

        @Override
        public Quiz mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final String name = rs.getString(NAME_KEY);
            final Speciality speciality = new Speciality();
            speciality.setId(rs.getLong(SPECIALITY_ID_KEY));
            return new Quiz(id, name, speciality);
        }
    }
}
