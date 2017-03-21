package com.getprepared.persistence.dao.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.dao.ResultDao;
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

import static com.getprepared.core.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.core.constant.PropertyConstants.KEYS;
import static com.getprepared.core.util.PropertyUtils.initProp;
import static com.getprepared.persistence.domain.Entity.ID_KEY;
import static com.getprepared.persistence.domain.Result.*;

/**
 * Created by koval on 06.01.2017.
 */
@Component("resultDao")
public class ResultDaoImpl implements ResultDao {

    private static final Properties prop = initProp(FILES_NAMES.RESULT);

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(final Result result) throws EntityExistsException {
        jdbcTemplate.executeUpdate(prop.getProperty(KEYS.SAVE), result,
                ps -> {
                    ps.setLong(1, result.getQuiz().getId());
                    ps.setLong(2, result.getUser().getId());
                    ps.setByte(3, result.getMark());
                    ps.setTimestamp(4, Timestamp.valueOf(result.getCreationDateTime()));
                });
    }

    @Override
    public Result findById(final Long id) throws EntityNotFoundException {
        return jdbcTemplate.singleQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    @Override
    public List<Result> findByUserId(Long id) {
        return jdbcTemplate.executeQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), USER_ID_KEY),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    private static class ResultMapper implements RowMapper<Result> {

        @Override
        public Result mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final User user = new User();
            user.setId(rs.getLong(USER_ID_KEY));
            final Quiz quiz = new Quiz();
            quiz.setId(rs.getLong(QUIZ_ID_KEY));
            final Byte mark = rs.getByte(MARK_KEY);
            final LocalDateTime dateTime = rs.getTimestamp(CREATION_DATETIME_KEY).toLocalDateTime();
            return fillResult(id, quiz, user, mark, dateTime);
        }

        private Result fillResult(final Long id, final Quiz quiz, final User user, final Byte mark,
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
