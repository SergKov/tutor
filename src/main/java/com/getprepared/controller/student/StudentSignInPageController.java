package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants.NAMES;
import com.getprepared.constant.PageConstants.PAGES;
import com.getprepared.controller.common.AbstractSignInController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 14.01.2017.
 */
public class StudentSignInPageController extends AbstractSignInController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute(TITLE, getMessages().getMessage(NAMES.SIGN_IN, request.getLocale()));
        fillPage(request);
        return PAGES.STUDENT_SIGN_IN;
    }
}