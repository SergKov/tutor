package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.converter.Converter;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;
import com.getprepared.web.validation.ValidationService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static org.apache.commons.collections4.MapUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.QUIZZES, request.getLocale()));

        final Long id = ((User) request.getSession().getAttribute(SESSION_ATTRIBUTE.TUTOR)).getId();

        final String currentPage = request.getParameter(INPUT.CURRENT_PAGE);
        final String numberOfElements = request.getParameter(INPUT.NUMBER_OF_ELEMENTS);

        if (isNumeric(currentPage) && isNumeric(numberOfElements)) {
            final PageableData pagination = new PageableData();
            pagination.setCurrentPage(Long.parseLong(currentPage));
            pagination.setNumberOfElements(Long.parseLong(numberOfElements));

            final List<Quiz> quizzes = quizService.findAllByTutorId(id, pagination);
            request.setAttribute(PAGINATION, pagination);
            request.setAttribute(QUIZZES, quizzes);
        }
    }
}
