package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static com.getprepared.web.constant.WebConstant.INPUT.QUIZ;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.QUIZZES;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 19.01.2017.
 */
public abstract class AbstractStudentHomePageCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService) {

        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.HOME_PAGE, request.getLocale()));

//        final List<Quiz> quizList = quizService.findAllActive(new PageableData()); // TODO
//
//        if (!CollectionUtils.isEmpty(quizList)) {
//            request.setAttribute(QUIZZES, quizList);
//        }
    }
}
