package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.dto.TestQuestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.LINK;
import static com.getprepared.web.constant.ApplicationConstant.PATH;
import static com.getprepared.web.constant.PropertyConstant.KEY.TEST;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 30.01.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_TEST)
public class StudentChangeQuestionCommand extends AbstractTestCommand {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, messages.getMessage(TEST, request.getLocale()));

        @SuppressWarnings("unchecked") final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTE.TEST);

        Integer parsedQuestionNumber = parseQuestionNumber(request.getParameter(INPUT.QUESTION_NUMBER), test);

        request.setAttribute(REQUEST_ATTRIBUTE.TEST_QUESTION, test.get(parsedQuestionNumber - 1));
        request.setAttribute(REQUEST_ATTRIBUTE.CURRENT_QUESTION, parsedQuestionNumber);

        return PATH.STUDENT_TEST;
    }
}
