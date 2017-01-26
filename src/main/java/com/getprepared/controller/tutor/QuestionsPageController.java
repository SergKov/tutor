package com.getprepared.controller.tutor;

import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUIZ_SERVICE;

/**
 * Created by koval on 25.01.2017.
 */
public class QuestionsPageController extends AbstractQuestionsController {

    private QuizService quizService;
    private QuestionService questionService;

    @Override
    public void init() {
        quizService = getServiceFactory().getService(QUIZ_SERVICE, QuizService.class);
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request, quizService, questionService);
        return PAGES.QUESTIONS;
    }
}
