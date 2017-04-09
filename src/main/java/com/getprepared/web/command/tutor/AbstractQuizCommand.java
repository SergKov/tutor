package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PropertyConstant.KEY;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizCommand implements Command {

    public static final long DEFAULT_PAGE_NUMBER = 1L;
    public static final long DEFAULT_NUMBER_OF_ELEMENTS = 5L;

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(KEY.QUIZZES, request.getLocale()));

        final User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE.TUTOR);

        final String currentPageParameter = request.getParameter(INPUT.CURRENT_PAGE);
        final String showElementsParameter = request.getParameter(INPUT.SHOW_ELEMENTS);

        final Long currentPage = isNumeric(currentPageParameter)
                ? Long.parseLong(currentPageParameter)
                : DEFAULT_PAGE_NUMBER;

        final Long showElements = isNumeric(showElementsParameter)
                ? Long.parseLong(showElementsParameter)
                : DEFAULT_NUMBER_OF_ELEMENTS;

        final PageableData pagination = new PageableData();
        pagination.setCurrentPage(currentPage);
        pagination.setShowElements(showElements);

        final List<Quiz> quizzes = quizService.findAllByTutorId(user.getId(), pagination);
        request.setAttribute(PAGINATION, pagination);
        request.setAttribute(QUIZZES, quizzes);
    }
}
