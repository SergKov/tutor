package com.getprepared.controller.tutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.*;

/**
 * Created by koval on 27.01.2017.
 */
public class QuestionAddPageController extends AbstractQuestionAddController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_ADD_QUESTION;
    }
}
