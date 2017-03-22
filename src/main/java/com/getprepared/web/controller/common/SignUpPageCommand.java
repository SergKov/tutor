package com.getprepared.web.controller.common;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 17.01.2017.
 */
@Controller
@RequestMapping(value = "/sign-up")
public class SignUpPageCommand extends AbstractSignUpCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.SIGN_UP;
    }
}
