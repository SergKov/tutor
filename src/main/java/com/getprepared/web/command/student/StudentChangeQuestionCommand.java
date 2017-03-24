package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.constant.PageConstants;
import com.getprepared.web.command.Command;
import com.getprepared.web.dto.TestQuestion;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.web.constant.PageConstants.PAGES;
import static com.getprepared.web.constant.PageConstants.REDIRECT;
import static com.getprepared.web.constant.WebConstants.*;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 30.01.2017.
 */
@Controller
@RequestMapping(PAGES.STUDENT_TEST)
public class StudentChangeQuestionCommand implements Command {

    private static final Logger LOG = Logger.getLogger(StudentChangeQuestionCommand.class);

    protected static final int FIRST_QUESTION = 1;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST) == null) {
            response.sendRedirect(PageConstants.LINKS.NOT_FOUND);
            return REDIRECT;
        }

        request.setAttribute(TITLE, messages.getMessage(PageConstants.NAMES.TEST, request.getLocale()));

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);

        Integer questionNumber;
        try {
            questionNumber = Integer.valueOf(request.getParameter(INPUTS.QUESTION_NUMBER));
            if (questionNumber > test.size() || questionNumber <= 0) {
                questionNumber = FIRST_QUESTION;
            }
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            questionNumber = FIRST_QUESTION;
        }

        request.setAttribute(REQUEST_ATTRIBUTES.TEST_QUESTION, test.get(questionNumber - 1));
        request.setAttribute(REQUEST_ATTRIBUTES.CURRENT_QUESTION, questionNumber);

        return PAGES.STUDENT_TEST;
    }
}
