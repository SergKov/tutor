package com.getprepared.controller.student;

import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.domain.Quiz;
import com.getprepared.service.QuizService;
import com.getprepared.util.impl.Messages;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.INPUTS.QUIZ;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUIZZES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageController implements Controller {

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
