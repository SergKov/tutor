package com.getprepared.controller.tutor;

import com.getprepared.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;

/**
 * Created by koval on 22.01.2017.
 */
@Bean("quizPageAddController")
public class QuizPageAddController extends AbstractQuizAddController {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_ADD_QUIZ;
    }
}
