package com.getprepared.infrastructure.template;

import com.getprepared.domain.Entity;
import com.getprepared.exception.DataAccessException;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.infrastructure.connection.TransactionalConnectionProvider;
import com.getprepared.infrastructure.template.function.PreparedStatementSetter;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koval on 11.01.2017.
 */
public class JdbcTemplate {

    private static final Logger LOG = Logger.getLogger(JdbcTemplate.class);

    private TransactionalConnectionProvider provider;

    public JdbcTemplate(TransactionalConnectionProvider provider) {
        this.provider = provider;
    }

    public void executeUpdate(final String sql, final Entity entity, final PreparedStatementSetter setter,
                              final int genKey) throws EntityExistsException {

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql, genKey)) {
            setter.setValues(ps);

            executeUpdate(ps, sql);

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getLong(1));
            } else {
                LOG.error(String.format("Failed to save entity %s", entity.getEntityName()));
                throw new DataAccessException();
            }

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute update %s with generated key %d", sql, genKey), e);
            throw new DataAccessException(e);
        }
    }

    public void executeUpdate(final String sql, final PreparedStatementSetter setter) throws EntityExistsException {

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            executeUpdate(ps, sql);
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute update %s", sql), e);
            throw new DataAccessException(e);
        }
    }

    private void executeUpdate(final PreparedStatement ps, final String sql) throws EntityExistsException {
        try {
            ps.executeUpdate();
        } catch (final SQLException e) {
            checkException(e, sql);
        }
    }

    private void checkException(final SQLException e, final String sql) throws EntityExistsException {
        if (e.getErrorCode() == 1062) {
            final String errorMsg = String.format("Entity by this query %s is already exists", sql);
            LOG.warn(errorMsg);
            throw new EntityExistsException(String.format("Entity by this query %s is already exists", sql));
        }
    }

    public void remove(final String sql, final PreparedStatementSetter setter) {

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            ps.executeUpdate();
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to remove %s", sql), e);
            throw new DataAccessException(e);
        }
    }


    public <T> T singleQuery(final String sql, final PreparedStatementSetter setter,
                                       final RowMapper<T> rowMapper) throws EntityNotFoundException {

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            setter.setValues(ps);

            final ResultSet rs = ps.executeQuery();
            rs.next();

            final T entry = rowMapper.mapRow(rs);

            if (rowMapper.mapRow(rs) == null) {
                final String errorMsg = String.format("Entity is not found by this query %s", sql);
                LOG.warn(errorMsg);
                throw new EntityNotFoundException(errorMsg);
            }

            return entry;

        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute singleQuery %s", sql), e);
            throw new DataAccessException(e);
        }
    }

    public <T> T singleQuery(final String sql, final RowMapper<T> rowMapper) throws EntityNotFoundException {
        return singleQuery(sql, new DefaultPreparedStatementSetter(), rowMapper);
    }

    public <T> List<T> executeQuery(final String sql, final PreparedStatementSetter setter,
                                 final RowMapper<T> rowMapper) {

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            setter.setValues(ps);
            final ResultSet rs = ps.executeQuery();

            final List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }

            return result;
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute singleQuery %s", sql), e);
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> executeQuery(final String sql, final RowMapper<T> rowMapper) {
        return executeQuery(sql, new DefaultPreparedStatementSetter(), rowMapper);
    }
}
