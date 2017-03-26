package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuestionService;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.annotation.RequestMethod;
import com.getprepared.web.constant.PageConstants;
import com.getprepared.web.dto.TestQuestion;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.REDIRECT;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 19.01.2017.
 */
@Controller
@RequestMapping(value = COMMANDS.STUDENT_TEST_START, method = RequestMethod.POST)
public class StudentStartTestCommand extends AbstractStudentHomePageCommand {

    private static final Logger LOG = Logger.getLogger(StudentStartTestCommand.class);

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUTS.QUIZ_ID);

        try {
            final Long parsedQuizId = Long.parseLong(quizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.QUIZ_ID, parsedQuizId);

            final List<TestQuestion> test = questionService.startTest(parsedQuizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTES.TEST, test);
            response.sendRedirect(LINKS.STUDENT_TEST);
            return REDIRECT;
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
            return REDIRECT;
        }
    }
}
