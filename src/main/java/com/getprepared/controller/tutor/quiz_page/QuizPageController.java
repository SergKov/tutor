package com.getprepared.controller.tutor.quiz_page;

import com.getprepared.controller.common.abstract_classes.AbstractQuizController;
import com.getprepared.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;

/**
 * Created by koval on 22.01.2017.
 */
public class QuizPageController extends AbstractQuizController {

    private QuizService quizService;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request, quizService);
        return PAGES.TUTOR_QUIZZES;
    }
}
