package com.getprepared.controller.common.abstract_classes;

import com.getprepared.constant.PageConstants;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ParseException;
import com.getprepared.infrastructure.pagination.Page;
import com.getprepared.service.SpecialityService;
import com.getprepared.utils.Parser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractSpecialityController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractSpecialityController.class);

    private final Parser parser = getUtilsFactory().getUtil(PARSER, Parser.class);

    protected void fillPage(final HttpServletRequest request, final SpecialityService specialityService) {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.SPECIALITIES, request.getLocale()));

        try {
            final Long startIndex = parser.parseLong(request.getParameter(START_INDEX));
            final Long endIndex = parser.parseLong(request.getParameter(END_INDEX));
            final Page<Speciality> specialities = specialityService.findAll(startIndex, endIndex);
            if (!specialities.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTES.SPECIALITIES, specialities);
            }
        } catch (final ParseException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.SPECIALITY_ID_INCORRECT,
                    request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.SPECIALITY_IS_NOT_FOUND,
                    request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }
    }
}
