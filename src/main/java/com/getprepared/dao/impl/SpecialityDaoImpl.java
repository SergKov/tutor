package com.getprepared.dao.impl;

import com.getprepared.dao.SpecialityDao;
import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
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
import static com.getprepared.domain.Speciality.NAME_KEY;

/**
 * Created by koval on 14.01.2017.
 */
public class SpecialityDaoImpl extends AbstractDao<Speciality> implements SpecialityDao {

    private static final Properties prop = PropertyUtils.initProp(FILES_NAMES.SPECIALITY);

    public SpecialityDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void save(final Speciality speciality) throws EntityExistsException {
        getJdbcTemplate().executeUpdate(prop.getProperty(KEYS.SAVE), speciality,
                ps -> ps.setString(1, speciality.getName()), PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Speciality findById(final Long id) throws EntityNotFoundException {
        return getJdbcTemplate().singleQuery(prop.getProperty(KEYS.FIND_BY_ID), ps -> ps.setLong(1, id),
                new SpecialityMapper());
    }

    @Override
    public List<Speciality> findAll() {
        //TODO
        return null;
    }

    private static class SpecialityMapper implements RowMapper<Speciality> {

        @Override
        public Speciality mapRow(final ResultSet rs) throws SQLException {
            final Long id = rs.getLong(ID_KEY);
            final String name = rs.getString(NAME_KEY);
            return new Speciality(id, name);
        }
    }
}
