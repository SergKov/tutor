package com.getprepared.controller.student;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;

/**
 * Created by koval on 21.01.2017.
 */
@Bean("studentHomePage")
public class StudentHomePageController extends AbstractStudentHomePageController {

    @Inject
    private QuizService quizService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request, quizService);
        return PAGES.STUDENT_HOME_PAGE;
    }
}
