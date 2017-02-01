package com.getprepared.controller.tutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;

/**
 * Created by koval on 22.01.2017.
 */
public class QuizPageAddController extends AbstractQuizAddController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_ADD_QUIZ;
    }
}
