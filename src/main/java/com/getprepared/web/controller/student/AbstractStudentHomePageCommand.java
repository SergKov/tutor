package com.getprepared.web.controller.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.controller.Command;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.PageConstants.NAMES;
import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstants.INPUTS.QUIZ;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.QUIZZES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(NAMES.HOME_PAGE, request.getLocale()));
        request.setAttribute(QUIZ, REGEX.QUIZ_NAME);

        final List<Quiz> quizList = quizService.findAllCreated();

        if (!CollectionUtils.isEmpty(quizList)) {
            request.setAttribute(QUIZZES, quizList);
        }
    }
}
