package com.getprepared.controller.tutor.speciality_page;

import com.getprepared.controller.common.abstract_classes.AbstractSpecialityController;
import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.SpecialityService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.ServerConstants.SERVICES.SPECIALITY_SERVICE;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;

/**
 * Created by koval on 21.01.2017.
 */
public class AddSpecialityController extends AbstractSpecialityController {

    private static final Logger LOG = Logger.getLogger(AddSpecialityController.class);

    private SpecialityService specialityService;

    @Override
    public void init() {
        specialityService = getServiceFactory().getService(SPECIALITY_SERVICE, SpecialityService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            final Speciality speciality = new Speciality();
            speciality.setName(request.getParameter(INPUTS.ADD_SPECIALITY));
            specialityService.save(speciality);
            response.sendRedirect(LINKS.TUTOR_SPECIALITIES);
            return REDIRECT;
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.STUDENT_INVALIDATED, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final EntityExistsException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.SPECIALITY_EXISTS, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }

        fillPage(request, specialityService);

        return PAGES.TUTOR_SPECIALITIES;
    }
}
