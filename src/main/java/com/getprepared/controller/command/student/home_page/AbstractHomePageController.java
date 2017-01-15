package com.getprepared.controller.command.student.home_page;

import com.getprepared.controller.AbstractController;
import com.getprepared.controller.Controller;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.UtilsConstant.REGEX;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractHomePageController extends AbstractController {

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute("emailRegex", REGEX.EMAIL);
        request.setAttribute("passwordRegex", REGEX.PASSWORD);
    }
}
