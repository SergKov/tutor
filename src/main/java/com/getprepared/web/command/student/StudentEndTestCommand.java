package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.ResultService;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.Command;
import com.getprepared.web.dto.TestQuestion;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 05.02.2017.
 */
@Controller
@CommandMapping(COMMAND.STUDENT_TEST_END)
public class StudentEndTestCommand implements Command {

    private static final Logger LOG = Logger.getLogger(StudentEndTestCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Override
    @SuppressWarnings("unchecked")
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTE.TEST);

        final Long id = (Long) request.getSession().getAttribute(SESSION_ATTRIBUTE.QUIZ_ID);

        try {
            final Quiz quiz = quizService.findById(id);
            final User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTE.STUDENT);
            questionService.endTest(quiz.getId(), user.getId(), test);
            request.getSession().removeAttribute(SESSION_ATTRIBUTE.TEST);
            response.sendRedirect(LINK.STUDENT_RESULT);
        } catch (final EntityNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            response.sendError(SC_NOT_FOUND);
        }
        return REDIRECT;
    }
}
