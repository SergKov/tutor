package com.getprepared.dao.impl;

import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
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
        return getJdbcTemplate().singleQuery(prop.getProperty(KEYS.FIND_BY_ID),
                ps -> ps.setLong(1, id), new ResultMapper());
    }

    @Override
    public List<Result> findByUserEmail(final String email) {
        return getJdbcTemplate().executeQuery(prop.getProperty(KEYS.FIND_BY_EMAIL), ps -> ps.setString(1, email),
                new ResultMapper());
    }

    private static class ResultMapper implements RowMapper<Result> {

        @Override
        public Result mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final User user = new User();
            user.setId(rs.getLong(USER_ID_KEY));
            final Byte mark = rs.getByte(MARK_KEY);
            final String specialityName = rs.getString(SPECIALITY_KEY);
            final String quizName = rs.getString(QUIZ_NAME_KEY);
            final LocalDateTime dateTime = rs.getTimestamp(CREATION_DATETIME_KEY).toLocalDateTime();
            return new Result(id, user, mark, specialityName, quizName, dateTime);
        }
    }
}
