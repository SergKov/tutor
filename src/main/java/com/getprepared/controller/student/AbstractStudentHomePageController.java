package com.getprepared.controller.student;

import com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.CollectionUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.PageConstants.ERRORS;
import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS.QUIZ;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.*;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractStudentHomePageController.class);

    private final Validation validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, getMessages().getMessage(NAMES.HOME_PAGE, request.getLocale()));
        request.setAttribute(QUIZ, REGEX.QUIZ_NAME);

        try {
            final User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTES.STUDENT);

            validation.validateId(user.getId());
            final List<Quiz> quizList = quizService.findAll();

            if (!CollectionUtils.isEmpty(quizList)) {
                request.setAttribute(QUIZZES, quizList);
            }
        } catch (final ValidationException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.STUDENT_INVALIDATED, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }
    }
}
