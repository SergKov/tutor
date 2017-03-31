package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;
import com.getprepared.web.constant.WebConstant;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 21.01.2017.
 */
public abstract class AbstractQuizCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.QUIZZES, request.getLocale()));

        final Long id = ((User) request.getSession().getAttribute(WebConstant.SESSION_ATTRIBUTE.TUTOR)).getId();

        final List<Quiz> quizzes = quizService.findAllByTutorId(id, new PageableData()); // TODO
        if (!CollectionUtils.isEmpty(quizzes)) {
            request.setAttribute(REQUEST_ATTRIBUTE.QUIZZES, quizzes);
        }
    }
}
