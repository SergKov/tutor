package com.getprepared.web.controller.student;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 21.01.2017.
 */
@Component("studentHomePage")
public class StudentHomePageController extends AbstractStudentHomePageController {

    @Inject
    private QuizService quizService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request, quizService);
        return PAGES.STUDENT_HOME_PAGE;
    }
}
