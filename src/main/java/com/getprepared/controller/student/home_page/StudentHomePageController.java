package com.getprepared.controller.student.home_page;

import com.getprepared.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;

/**
 * Created by koval on 19.01.2017.
 */
public class StudentHomePageController extends AbstractStudentHomePageController {

    private QuizService quizService;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request, quizService);
        return PAGES.STUDENT_HOME_PAGE;
    }
}
