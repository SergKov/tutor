package com.getprepared.dao.impl;

import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.database.template.JdbcTemplate;
import com.getprepared.database.template.function.RowMapper;
import com.getprepared.utils.impl.PropertyUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Result.*;

/**
 * Created by koval on 06.01.2017.
 */
public class ResultDaoImpl extends AbstractDao<Result> implements ResultDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.RESULT);

    public ResultDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Result result) throws EntityExistsException {
        getJdbcTemplate().executeUpdate(prop.getProperty(KEYS.SAVE), result,
                ps -> {
                    ps.setLong(1, result.getUser().getId());
                    ps.setByte(2, result.getMark());
                    ps.setString(3, result.getQuizName());
                    ps.setTimestamp(4, Timestamp.valueOf(result.getCreationDateTime()));
                }, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Result findById(final Long id) throws EntityNotFoundException {
        return getJdbcTemplate().singleQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), ID_KEY),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    @Override
    public List<Result> findByUserId(Long id) throws EntityNotFoundException {
        return getJdbcTemplate().executeQuery(String.format(prop.getProperty(KEYS.FIND_BY_ID), USER_ID_KEY),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    private static class ResultMapper implements RowMapper<Result> {

        @Override
        public Result mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final User user = new User();
            user.setId(rs.getLong(USER_ID_KEY));
            final Byte mark = rs.getByte(MARK_KEY);
            final String quizName = rs.getString(QUIZ_NAME_KEY);
            final LocalDateTime dateTime = rs.getTimestamp(CREATION_DATETIME_KEY).toLocalDateTime();
            return fillResult(id, user, mark, quizName, dateTime);
        }

        private Result fillResult(final Long id, final User user, final Byte mark, final String name,
                                  final LocalDateTime dateTime) {
            final Result result = new Result();
            result.setId(id);
            result.setUser(user);
            result.setMark(mark);
            result.setQuizName(name);
            result.setCreationDateTime(dateTime);
            return result;
        }
    }
}
