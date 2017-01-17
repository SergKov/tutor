package com.getprepared.controller.student.home_page;

import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.constant.PageConstants.PAGES;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.PageConstants.NAMES.*;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 14.01.2017.
 */
public class HomePageController extends AbstractHomePageController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession(false) != null) {
            response.sendRedirect(LINKS.CHOOSE_TEST);
            return REDIRECT;
        }

        request.setAttribute(TITLE, getMessages().getMessage(SIGN_IN, request.getLocale()));
        fillPage(request);
        return PAGES.HOME;
    }
}
