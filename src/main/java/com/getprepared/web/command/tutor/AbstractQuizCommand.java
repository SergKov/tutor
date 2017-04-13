package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.command.AbstractPageableCommand;
import com.getprepared.web.constant.PropertyConstant.KEY;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizCommand extends AbstractPageableCommand {

    private static final String QUIZZES = "quizzes";

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(KEY.QUIZZES, request.getLocale()));

        final User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE.TUTOR);

        final PageableData pagination = doPageable(request, QUIZZES);

        final List<Quiz> quizzes = quizService.findAllByTutorId(user.getId(), pagination);
        request.setAttribute(REQUEST_ATTRIBUTE.QUIZZES, quizzes);
    }
}
