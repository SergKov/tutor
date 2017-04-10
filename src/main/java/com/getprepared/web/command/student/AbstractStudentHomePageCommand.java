package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PropertyConstant;
import com.getprepared.web.constant.WebConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_NUMBER_OF_ELEMENTS;
import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_PAGE_NUMBER;
import static com.getprepared.web.constant.PropertyConstant.KEY.HOME_PAGE;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.PAGINATION;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.QUIZZES;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE.*;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(PropertyConstant.KEY.QUIZZES, request.getLocale()));

        final String currentPageParameter = request.getParameter(INPUT.CURRENT_PAGE);
        final String showElementsParameter = request.getParameter(INPUT.SHOW_ELEMENTS);

        Long currentPage;
        if (isNumeric(currentPageParameter)) {
            currentPage = Long.parseLong(currentPageParameter);
        } else {
            currentPage = (Long) request.getSession().getAttribute(QUIZZES_CURRENT_PAGE);
            if (currentPage == null) {
                currentPage = DEFAULT_PAGE_NUMBER;
            }
        }

        Long showElements;
        if (isNumeric(showElementsParameter)) {
            showElements = Long.parseLong(showElementsParameter);
            currentPage = DEFAULT_PAGE_NUMBER;
        } else {
            showElements = (Long) request.getSession().getAttribute(QUIZZES_SHOW_ELEMENTS);
            if (showElements == null) {
                showElements = DEFAULT_NUMBER_OF_ELEMENTS;
            }
        }

        request.getSession().setAttribute(QUIZZES_CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(QUIZZES_SHOW_ELEMENTS, showElements);

        final PageableData pagination = new PageableData();
        pagination.setCurrentPage(currentPage);
        pagination.setShowElements(showElements);

        List<Quiz> quizzes = quizService.findAllActive(pagination);
        request.setAttribute(PAGINATION, pagination);
        request.setAttribute(QUIZZES, quizzes);
    }
}
