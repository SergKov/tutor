package com.getprepared.controller.student;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.domain.Quiz;
import com.getprepared.domain.Result;
import com.getprepared.domain.User;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.service.ResultService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 05.02.2017.
 */
@Bean("studentEndTest")
public class StudentEndTestController implements Controller {

    private static final Logger LOG = Logger.getLogger(StudentEndTestController.class);

    @Inject
    private QuizService quizService;

    @Inject
    private QuestionService questionService;

    @Inject
    private ResultService resultService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);
        final double mark = questionService.endTest(test);
        request.getSession().removeAttribute(SESSION_ATTRIBUTES.TEST);
        request.getSession().setAttribute(SESSION_ATTRIBUTES.MARK, mark);

        final Long id = (Long) request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ_ID);

        try {
            final Quiz quiz = quizService.findById(id);
            final Result result = new Result();
            result.setQuiz(quiz);
            final User user = (User) request.getSession().getAttribute(SESSION_ATTRIBUTES.STUDENT);
            result.setUser(user);
            result.setMark((byte) mark);
            result.setCreationDateTime(LocalDateTime.now());
            resultService.save(result);
        } catch (final EntityNotFoundException | EntityExistsException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(LINKS.NOT_FOUND);
        }

        response.sendRedirect(LINKS.STUDENT_GET_RESULT);
        return REDIRECT;
    }
}
