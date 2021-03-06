package com.getprepared.web.command;

import com.getprepared.persistence.database.pagination.PageableData;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_NUMBER_OF_ELEMENTS;
import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_PAGE_NUMBER;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.PAGINATION;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 11.04.2017.
 */
public abstract class AbstractPageableCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AbstractPageableCommand.class);

    protected PageableData doPageable(final HttpServletRequest request, final String type) {

        final String currentPageParameter = request.getParameter(INPUT.CURRENT_PAGE);
        final String showElementsParameter = request.getParameter(INPUT.SHOW_ELEMENTS);

        Long currentPage;
        if (isNumeric(currentPageParameter)) {
            currentPage = parseLong(currentPageParameter) != 0 ?
                    Long.valueOf(currentPageParameter)
                    : DEFAULT_PAGE_NUMBER;
        } else {
            currentPage = (Long) request.getSession().getAttribute(type + SESSION_ATTRIBUTE.CURRENT_PAGE);
            if (currentPage == null) {
                currentPage = DEFAULT_PAGE_NUMBER;
            }
        }

        Long showElements;
        if (isNumeric(showElementsParameter)) {
            showElements = parseLong(showElementsParameter) != 0 ?
                    Long.valueOf(showElementsParameter)
                    : DEFAULT_NUMBER_OF_ELEMENTS;

            currentPage = DEFAULT_PAGE_NUMBER;
        } else {
            showElements = (Long) request.getSession().getAttribute(type + SESSION_ATTRIBUTE.SHOW_ELEMENTS);
            if (showElements == null) {
                showElements = DEFAULT_NUMBER_OF_ELEMENTS;
            }
        }

        request.getSession().setAttribute(type + SESSION_ATTRIBUTE.CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(type + SESSION_ATTRIBUTE.SHOW_ELEMENTS, showElements);

        final PageableData pagination = new PageableData();
        pagination.setCurrentPage(currentPage);
        pagination.setShowElements(showElements);

        request.setAttribute(PAGINATION, pagination);

        return pagination;
    }

    private Long parseLong(final String number) {
        try {
            return Long.valueOf(number);
        } catch (final NumberFormatException e) {
            LOG.error(e.getMessage(), e);
            return 0L;
        }
    }
}
