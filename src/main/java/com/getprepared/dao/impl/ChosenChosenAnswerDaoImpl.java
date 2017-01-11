package com.getprepared.dao.impl;

import com.getprepared.dao.ChosenAnswerDao;
import com.getprepared.domain.ChosenAnswer;
import com.getprepared.domain.QuestionHistory;
import com.getprepared.exception.DataAccessException;
import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.TransactionalConnectionProvider;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PropertyConstants.FILES_NAMES;
import static com.getprepared.constant.PropertyConstants.KEYS;
import static com.getprepared.domain.ChosenAnswer.QUESTION_ID_KEY;
import static com.getprepared.domain.ChosenAnswer.TEXT_KEY;
import static com.getprepared.domain.Entity.ID_KEY;

/**
 * Created by koval on 06.01.2017.
 */
public class ChosenChosenAnswerDaoImpl extends AbstractDao<ChosenAnswer> implements ChosenAnswerDao {

    private static final Logger LOG = Logger.getLogger(ChosenChosenAnswerDaoImpl.class);

    public ChosenChosenAnswerDaoImpl(JdbcTemplate template) {
        super(template);
    }

    @Override
    public void save(ChosenAnswer answer) {
        //TODO
    }

    @Override
    public List<ChosenAnswer> findByQuestionId(Long questionId) {
        //TODO throw exception
        return null;
    }

    private static class ChosenAnswerMapper implements RowMapper<ChosenAnswer> {

        @Override
        public ChosenAnswer mapRow(ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final QuestionHistory question = new QuestionHistory();
            question.setId(rs.getLong(QUESTION_ID_KEY));
            final String text = rs.getString(TEXT_KEY);
            return new ChosenAnswer(id, question, text);
        }
    }
}
