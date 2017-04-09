package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PropertyConstant;
import com.getprepared.web.constant.WebConstant;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.getprepared.web.constant.PropertyConstant.KEY.HOME_PAGE;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.PAGINATION;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.QUIZZES;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageCommand implements Command {

    public static final long DEFAULT_PAGE_NUMBER = 1L;
    public static final long DEFAULT_NUMBER_OF_ELEMENTS = 5L;

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(PropertyConstant.KEY.QUIZZES, request.getLocale()));

        final User user = (User) request.getSession().getAttribute(WebConstant.SESSION_ATTRIBUTE.TUTOR);

        final String currentPageParameter = request.getParameter(WebConstant.INPUT.CURRENT_PAGE);
        final String showElementsParameter = request.getParameter(WebConstant.INPUT.SHOW_ELEMENTS);

        final Long currentPage = isNumeric(currentPageParameter)
                ? Long.parseLong(currentPageParameter)
                : DEFAULT_PAGE_NUMBER;

        final Long showElements = isNumeric(showElementsParameter)
                ? Long.parseLong(showElementsParameter)
                : DEFAULT_NUMBER_OF_ELEMENTS;

        final PageableData pagination = new PageableData();
        pagination.setCurrentPage(currentPage);
        pagination.setShowElements(showElements);

        final List<Quiz> quizzes = quizService.findAllActive(pagination);
        request.setAttribute(PAGINATION, pagination);
        request.setAttribute(QUIZZES, quizzes);
    }
}
