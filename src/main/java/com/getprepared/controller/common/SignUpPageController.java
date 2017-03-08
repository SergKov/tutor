package com.getprepared.controller.common;

import com.getprepared.annotation.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;

/**
 * Created by koval on 17.01.2017.
 */
@Component("signUpPage")
public class SignUpPageController extends AbstractSignUpController {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.SIGN_UP;
    }
}
