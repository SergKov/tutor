package com.getprepared.dao.impl;

import com.getprepared.dao.ResultDao;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static com.getprepared.domain.Entity.ID_KEY;
import static com.getprepared.domain.Result.*;

/**
 * Created by koval on 06.01.2017.
 */
public class ResultDaoImpl extends AbstractDao<Result> implements ResultDao {

    private static final Logger LOG = Logger.getLogger(ResultDaoImpl.class);

    public ResultDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(final Result result) {
        //TODO
    }

    @Override
    public Result findById(final Long id) throws EntityNotFoundException {
        //TODO throw exception
        return null;
    }

    @Override
    public List<Result> findByUserEmail(final String email) {
        //TODO add pagination, throw exception
        return null;
    }

    private static class ResultMapper implements RowMapper<Result> {

        @Override
        public Result mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final User user = new User();
            user.setId(rs.getLong(USER_ID_KEY));
            final Byte mark = rs.getByte(MARK_KEY);
            final String quizName = rs.getString(QUIZ_NAME_KEY);
            final LocalDateTime dateTime = rs.getTimestamp(CREATION_DATETIME_KEY).toLocalDateTime();
            return new Result(id, user, mark, quizName, dateTime);
        }
    }
}
