package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.WebConstant.INPUT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.PropertyConstant.KEY.ALL_RESULTS;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE.QUIZZES_CURRENT_PAGE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE.QUIZZES_SHOW_ELEMENTS;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 09.04.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_RESULTS)
public class StudentAllResultsPageCommand implements Command {

    @Inject
    private ResultService resultService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, messages.getMessage(ALL_RESULTS, request.getLocale()));

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

        final Long id = ((User)request.getSession().getAttribute(SESSION_ATTRIBUTE.STUDENT)).getId();

        final List<Result> results = resultService.findByUserId(id, pagination);
        request.setAttribute(PAGINATION, pagination);
        request.setAttribute(RESULTS, results);

        return PATH.STUDENT_RESULTS;
    }
}
