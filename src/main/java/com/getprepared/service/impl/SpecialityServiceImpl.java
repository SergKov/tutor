package com.getprepared.service.impl;

import com.getprepared.dao.SpecialityDao;
import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.SpecialityService;
import org.apache.log4j.Logger;

import java.util.List;

import static com.getprepared.constant.ServerConstants.DAOS.SPECIALITY_DAO;

/**
 * Created by koval on 15.01.2017.
 */
public class SpecialityServiceImpl extends AbstractService implements SpecialityService {

    private static final Logger LOG = Logger.getLogger(SpecialityServiceImpl.class);

    public SpecialityServiceImpl() { }

    @Override
    public void save(final Speciality speciality) throws ValidationException, EntityExistsException {
        try {
            getTransactionManager().begin();
            getValidation().validateSpeciality(speciality);
            final SpecialityDao specialityDao = getDao();
            specialityDao.save(speciality);
            getTransactionManager().commit();
        } catch (ValidationException | EntityExistsException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Speciality findById(final Long id) throws ValidationException, EntityNotFoundException {
        try {
            getTransactionManager().begin();
            getValidation().validateId(id);
            final SpecialityDao specialityDao = getDao();
            final Speciality speciality = specialityDao.findById(id);
            getTransactionManager().commit();
            return speciality;
        } catch (ValidationException | EntityNotFoundException e) {
            getTransactionManager().rollback();
            LOG.warn(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Speciality> findAll() {
        //TODO
        return null;
    }

    private SpecialityDao getDao() {
        return getDaoFactory().getDao(SPECIALITY_DAO, SpecialityDao.class);
    }
}
