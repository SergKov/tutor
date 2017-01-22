package com.getprepared.controller.common.abstract_classes;

import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.infrastructure.pagination.Page;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.ParserImpl;
import com.getprepared.utils.impl.ValidationImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.PageConstants.ERRORS;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractQuizController.class);

    private final ParserImpl parser = getUtilsFactory().getUtil(PARSER, ParserImpl.class);
    private final Validation validation = getUtilsFactory().getUtil(VALIDATION, ValidationImpl.class);

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.QUIZZES, request.getLocale()));

        try {
            final Long pageIndex = parser.parseLong(request.getParameter(PAGE_INDEX), 10L);
            validation.validateId(pageIndex);
            final Long pageSize = parser.parseLong(request.getParameter(PAGE_SIZE), 0L);
            validation.validateId(pageSize);
            final Long specialityId = (Long) request.getSession().getAttribute(SESSION_ATTRIBUTES.SPECIALITY_ID);
            final Page<Quiz> quizzes = quizService.findAllBySpecialityId(specialityId, pageIndex, pageSize);
            if (!quizzes.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTES.QUIZ_LIST, quizzes);
            }
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.QUIZ_EXISTS, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }
    }
}
