package com.getprepared.dao.impl;

import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.pagination.Page;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import com.getprepared.utils.impl.PropertyUtils;

import java.sql.PreparedStatement;
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
public class QuizDaoImpl extends AbstractDao<Quiz> implements QuizDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.QUIZ);

    public QuizDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        getJdbcTemplate().executeUpdate(prop.getProperty(KEYS.SAVE), quiz, ps -> ps.setString(1, quiz.getName()),
                PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        return getJdbcTemplate().singleQuery(prop.getProperty(KEYS.FIND_BY_ID),
                ps -> ps.setLong(1, id), new QuizMapper());
    }

    @Override
    public List<Quiz> findByUserId(final Long id) {
        return getJdbcTemplate().executeQuery(prop.getProperty(KEYS.FIND_BY_USER_ID), ps -> ps.setLong(1, id),
                new QuizMapper());
    }

    @Override
    public List<Quiz> findByUserEmail(final String email) {
        return getJdbcTemplate().executeQuery(prop.getProperty(KEYS.FIND_BY_EMAIL), ps -> ps.setString(1, email),
                new QuizMapper());
    }

    @Override
    public void assign(final Long userId, final Long quizId) throws EntityExistsException {
        getJdbcTemplate().executeUpdate(prop.getProperty(KEYS.ASSIGN),
                ps -> {
                    ps.setLong(1, userId);
                    ps.setLong(2, quizId);
                });
    }

    @Override
    public Page<Quiz> findAll(Long page, Long pageSize) throws EntityNotFoundException {

        final List<Quiz> quizzes = getJdbcTemplate()
                .executeQuery(String.format(prop.getProperty(KEYS.FIND_ALL), pageSize, page * pageSize),
                        new QuizMapper());

        final Long count = getJdbcTemplate().singleQuery(prop.getProperty(KEYS.COUNT), rs -> rs.getLong(1));

        return new Page<>(quizzes, count, page, pageSize);
    }

    @Override
    public void remove(final Long id) {
        getJdbcTemplate().remove(prop.getProperty(KEYS.REMOVE_BY_ID), ps -> ps.setLong(1, id));
    }

    private static class QuizMapper implements RowMapper<Quiz> {

        @Override
        public Quiz mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final String name = rs.getString(NAME_KEY);
            return new Quiz(id, name);
        }
    }
}
