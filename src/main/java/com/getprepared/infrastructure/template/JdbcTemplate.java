package com.getprepared.infrastructure.template;

import com.getprepared.domain.Entity;
import com.getprepared.exception.DataAccessException;
import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.template.function.PreparedStatementSetter;
import com.getprepared.infrastructure.template.function.RowMapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by koval on 11.01.2017.
 */
public class JdbcTemplate {

    private static final Logger LOG = Logger.getLogger(JdbcTemplate.class);

    private ConnectionProvider provider;

    public JdbcTemplate(ConnectionProvider provider) {
        this.provider = provider;
    }

    public void executeUpdate(final String sql, final Entity entity, final PreparedStatementSetter setter,
                              final int genKey) { // save

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql, genKey)) {

            setter.setValues(ps);
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute update %s with generated key %d", sql, genKey), e);
            throw new DataAccessException(e);
        }
    }

    public void executeUpdate(final String sql, final PreparedStatementSetter setter) { // update, delete

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            setter.setValues(ps);
            ps.executeUpdate();
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute update %s", sql), e);
            throw new DataAccessException(e);
        }
    }

    public <T> Optional<T> query(final String sql, final PreparedStatementSetter setter,
                                 final RowMapper<T> rowMapper) { // findById

        final Connection con = provider.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){
            setter.setValues(ps);
            final ResultSet rs = ps.executeQuery();
            rs.next(); //TODO
            return Optional.of(rowMapper.mapRow(rs));
        } catch (final SQLException e) {
            LOG.error(String.format("Failed to execute query %s", sql), e);
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> executeQuery(final String sql, final PreparedStatementSetter setter,
                                 final RowMapper<T> rowMapper) { // findByOtherId

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
            LOG.error(String.format("Failed to execute query %s", sql), e);
            throw new DataAccessException(e);
        }
    }
}
