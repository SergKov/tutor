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
import com.getprepared.web.form.PageableForm;
import com.getprepared.web.validation.ValidationService;
import org.apache.commons.collections4.MapUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.ERROR_MSGS;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizCommand implements Command {

    @Inject
    private Messages messages;

    @Inject
    private Converter<PageableForm, PageableData> pageableFormConverter;

    @Inject
    private ValidationService validationService;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.QUIZZES, request.getLocale()));

        final Long id = ((User) request.getSession().getAttribute(SESSION_ATTRIBUTE.TUTOR)).getId();

        final PageableForm form = new PageableForm();
        form.setCurrentPage(request.getParameter(INPUT.CURRENT_PAGE));
        form.setNumberOfElements(request.getParameter(INPUT.NUMBER_OF_ELEMENTS));

        final Map<String, String> errors = validationService.validate(form);
        if (!MapUtils.isEmpty(errors)) {
            request.setAttribute(ERROR_MSGS, errors);
        } else {
            final List<Quiz> quizzes = quizService.findAllByTutorId(id, new PageableData());
            request.setAttribute(REQUEST_ATTRIBUTE.QUIZZES, quizzes);
        }
    }
}
