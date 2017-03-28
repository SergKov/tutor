package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuestionService;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.dto.TestQuestion;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;

/**
 * Created by koval on 19.01.2017.
 */
@Controller
@CommandMapping(COMMAND.STUDENT_TEST_START)
public class StudentStartTestCommand extends AbstractStudentHomePageCommand {

    private static final Logger LOG = Logger.getLogger(StudentStartTestCommand.class);

    @Inject
    private QuestionService questionService;

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(INPUT.QUIZ_ID);

        try {
            final Long parsedQuizId = Long.parseLong(quizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTE.QUIZ_ID, parsedQuizId);

            final List<TestQuestion> test = questionService.startTest(parsedQuizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTE.TEST, test);
            response.sendRedirect(LINK.STUDENT_TEST);
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINK.NOT_FOUND);
        }
        return REDIRECT;
    }
}
