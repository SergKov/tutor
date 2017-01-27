package com.getprepared.controller.tutor;

import com.getprepared.constant.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 27.01.2017.
 */
public class QuestionAddPageController extends AbstractQuestionAddController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute(TITLE, REQUEST_ATTRIBUTES.ADD_QUESTION);
        fillPage(request);
        return PageConstants.PAGES.ADD_QUESTION;
    }
}
