package com.getprepared.persistence.dao.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.util.PropertyUtils;
import com.getprepared.persistence.annotation.Repository;
import com.getprepared.persistence.dao.ResultDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.database.template.JdbcTemplate;
import com.getprepared.persistence.database.template.RowMapper;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static com.getprepared.context.Registry.getApplicationContext;
import static com.getprepared.context.constant.ServerConstant.PROPERTY_UTILS;
import static com.getprepared.persistence.constant.PropertyConstant.FILES_NAME;
import static com.getprepared.persistence.constant.PropertyConstant.KEY;
import static com.getprepared.persistence.domain.Entity.ID_KEY;
import static com.getprepared.persistence.domain.Result.*;

/**
 * Created by koval on 06.01.2017.
 */
@Repository("resultDao")
public class ResultDaoImpl implements ResultDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Properties prop = getApplicationContext().getBean(PROPERTY_UTILS, PropertyUtils.class)
            .getProperty(FILES_NAME.RESULT);

    @Override
    public void save(final Result result) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEY.SAVE), result,
                ps -> {
                    ps.setLong(1, result.getQuiz().getId());
                    ps.setLong(2, result.getUser().getId());
                    ps.setDouble(3, result.getMark());
                    ps.setTimestamp(4, Timestamp.valueOf(result.getCreationDateTime()));
                });
    }

    @Override
    public Result findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(String.format(prop.getProperty(KEY.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    @Override
    public List<Result> findByUserId(final Long id, final PageableData page) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_ALL),
                page.getLimit(), page.getOffset()),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    @Override
    public List<Result> findByQuizId(final Long quizId, final PageableData page) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEY.FIND_BY_ID), QUIZ_ID_KEY,
                page.getLimit(), page.getOffset()),
                ps -> ps.setLong(1, quizId), new ResultMapper());
    }

    @Override
    public Long countFoundRows(final Long id) {
        return jdbcTemplate.executeQuery(prop.getProperty(KEY.COUNT_ALL_BY_USER_ID), ps -> {
            ps.setLong(1, id);
        });
    }

    private static class ResultMapper implements RowMapper<Result> {

        @Override
        public Result mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final User user = new User();
            user.setId(rs.getLong(USER_ID_KEY));
            final Quiz quiz = new Quiz();
            quiz.setId(rs.getLong(QUIZ_ID_KEY));
            final Double mark = rs.getDouble(MARK_KEY);
            final LocalDateTime dateTime = rs.getTimestamp(CREATION_DATETIME_KEY).toLocalDateTime();
            return fillResult(id, quiz, user, mark, dateTime);
        }

        private Result fillResult(final Long id, final Quiz quiz, final User user, final Double mark,
                                  final LocalDateTime dateTime) {
            final Result result = new Result();
            result.setId(id);
            result.setQuiz(quiz);
            result.setUser(user);
            result.setMark(mark);
            result.setCreationDateTime(dateTime);
            return result;
        }
    }
}
