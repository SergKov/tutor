package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.command.AbstractPageableCommand;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.getprepared.web.constant.PropertyConstant.*;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageCommand extends AbstractPageableCommand {

    private static final String ACTIVE_QUIZZES = "activeQuizzes";

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(KEY.QUIZZES, request.getLocale()));

        final PageableData pagination = doPageable(request, ACTIVE_QUIZZES);
        List<Quiz> quizzes = quizService.findAllActive(pagination);

        request.setAttribute(REQUEST_ATTRIBUTE.QUIZZES, quizzes);
    }
}
