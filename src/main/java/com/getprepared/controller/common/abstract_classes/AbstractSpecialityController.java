package com.getprepared.controller.common.abstract_classes;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Speciality;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.infrastructure.pagination.Page;
import com.getprepared.service.SpecialityService;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.ParserImpl;
import com.getprepared.utils.impl.ValidationImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractSpecialityController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractSpecialityController.class);

    private final ParserImpl parser = getUtilsFactory().getUtil(PARSER, ParserImpl.class);
    private final Validation validation = getUtilsFactory().getUtil(VALIDATION, ValidationImpl.class);

    protected void fillPage(final HttpServletRequest request, final SpecialityService specialityService) {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.SPECIALITIES, request.getLocale()));

        try {
            final Long pageIndex = parser.parseLong(request.getParameter(PAGE_INDEX), 10L);
            validation.validateId(pageIndex);
            final Long pageSize = parser.parseLong(request.getParameter(PAGE_SIZE), 0L);
            validation.validateId(pageSize);
            final Page<Speciality> specialities = specialityService.findAll(pageIndex, pageSize);
            if (!specialities.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTES.SPECIALITIES, specialities);
            }
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.SPECIALITY_IS_NOT_FOUND,
                    request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }
    }
}
