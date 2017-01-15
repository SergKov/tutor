package com.getprepared.controller.command.student.home_page;

import com.getprepared.constant.PageConstants.LINKS;
import com.getprepared.constant.PageConstants.NAMES;
import com.getprepared.constant.PageConstants.PAGES;
import com.getprepared.utils.impl.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.REDIRECT;

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

        request.setAttribute("title", Messages.getInstance().getMessage(NAMES.SIGN_IN, request.getLocale()));
        fillPage(request);
        return PAGES.HOME;
    }
}
