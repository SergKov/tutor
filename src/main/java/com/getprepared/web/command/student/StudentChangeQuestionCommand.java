package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;
import com.getprepared.web.dto.TestQuestion;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 30.01.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_TEST)
public class StudentChangeQuestionCommand implements Command {

    private static final Logger LOG = Logger.getLogger(StudentChangeQuestionCommand.class);

    protected static final int FIRST_QUESTION = 1;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTE.TEST) == null) {
            response.sendRedirect(LINK.NOT_FOUND);
            return REDIRECT;
        }

        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.TEST, request.getLocale()));

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTE.TEST);

        final String questionNumber = request.getParameter(INPUT.QUESTION_NUMBER);

        Integer parsedQuestionNumber;
        if (isNumeric(questionNumber)) {
            parsedQuestionNumber = Integer.valueOf(request.getParameter(INPUT.QUESTION_NUMBER));
            if (parsedQuestionNumber > test.size() || parsedQuestionNumber <= 0) {
                parsedQuestionNumber = FIRST_QUESTION;
            }
        } else {
            parsedQuestionNumber = FIRST_QUESTION;
        }

        request.setAttribute(REQUEST_ATTRIBUTE.TEST_QUESTION, test.get(parsedQuestionNumber - 1));
        request.setAttribute(REQUEST_ATTRIBUTE.CURRENT_QUESTION, questionNumber);

        return PAGE.STUDENT_TEST;
    }
}
