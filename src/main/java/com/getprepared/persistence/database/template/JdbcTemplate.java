package com.getprepared.persistence.database.template;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.persistence.database.ConnectionProvider;
import com.getprepared.persistence.domain.Entity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koval on 11.01.2017.
 */
@Component
public class JdbcTemplate {

    private static final Logger LOG = Logger.getLogger(JdbcTemplate.class);

    private static final int SQL_DUPLICATE_ERROR_CODE = 1062;

    @Inject
    private ConnectionProvider connectionProvider;

    public void executeUpdate(final String sql, final Entity entity, final PreparedStatementSetter setter)
            throws EntityExistsException {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setter.setValues(ps);

            executeUpdate(ps, sql);

            final ResultSet rs = ps.getGeneratedKeys();
            initEntityId(entity, rs);
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute update %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public void batchSave(final String sql, final List<? extends Entity> entities,
                          final BatchPreparedStatementSetter batchSetter) throws EntityExistsException {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            addBatch(batchSetter, ps);
            ps.executeBatch();

            final ResultSet rs = ps.getGeneratedKeys();
            for (Entity entity : entities) {
                initEntityId(entity, rs);
            }
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute batch update %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public void batchUpdate(final String sql,
                            final BatchPreparedStatementSetter batchSetter)  {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            addBatch(batchSetter, ps);
            ps.executeBatch();
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute batch update %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    private void addBatch(final BatchPreparedStatementSetter batchSetter,
                          final PreparedStatement ps) throws SQLException {
        final int batchSize = batchSetter.getBatchSize();
        for (int i = 0; i < batchSize; i++) {
            batchSetter.setValues(ps, i);
            ps.addBatch();
        }
    }

    private void initEntityId(final Entity entity, final ResultSet rs) throws SQLException {
        if (rs.next()) {
            entity.setId(rs.getLong(1));
        } else {
            final String errorMsg = String.format("Failed to save entity %s", entity.getEntityName());
            LOG.error(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
    }

    public void update(final String sql, final PreparedStatementSetter setter) {
        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            ps.executeUpdate();
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute update %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public void executeUpdate(final String sql, final PreparedStatementSetter setter) throws EntityExistsException {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            executeUpdate(ps, sql);
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute update %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
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
        if (e.getErrorCode() == SQL_DUPLICATE_ERROR_CODE) {
            throw new EntityExistsException(String.format("Entity by this query %s is already exists", sql), e);
        }
    }

    public void executeUpdate(final String sql) {

        final Connection con = connectionProvider.getConnection();

        try (Statement ps = con.createStatement()) {
            ps.executeUpdate(sql);
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute update %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public void remove(final String sql, final PreparedStatementSetter setter) {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            ps.executeUpdate();
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to remove %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }


    public <T> T singleQuery(final String sql, final PreparedStatementSetter setter,
                                       final RowMapper<T> rowMapper) throws EntityNotFoundException {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            setter.setValues(ps);

            final ResultSet rs = ps.executeQuery();

            T entry;
            if (rs.next()) {
                entry = rowMapper.mapRow(rs);
            } else {
                throw new EntityNotFoundException(String.format("Entity is not found by this query %s", sql));
            }
            return entry;
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute singleQuery %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public <T> T singleQuery(final String sql, final RowMapper<T> rowMapper) throws EntityNotFoundException {
        return singleQuery(sql, new DefaultPreparedStatementSetter(), rowMapper);
    }

    public <T> List<T> executeQuery(final String sql, final PreparedStatementSetter setter,
                                    final RowMapper<T> rowMapper) {

        final Connection con = connectionProvider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            setter.setValues(ps);
            final ResultSet rs = ps.executeQuery();

            final List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }

            return result;
        } catch (final SQLException e) {
            final String errorMsg = String.format("Failed to execute query %s", sql);
            LOG.error(errorMsg, e);
            throw new IllegalStateException(errorMsg, e);
        }
    }

    public <T> List<T> executeQuery(final String sql, final RowMapper<T> rowMapper) {
        return executeQuery(sql, new DefaultPreparedStatementSetter(), rowMapper);
    }
}
