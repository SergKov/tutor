package com.getprepared.controller.common.abstract_classes;

import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.constant.UtilsConstant.REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.EMAIL_REGEX;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.PASSWORD_REGEX;

/**
 * Created by koval on 15.01.2017.
 */
public abstract class AbstractSignInController extends AbstractController {

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(EMAIL_REGEX, REGEX.EMAIL);
        request.setAttribute(PASSWORD_REGEX, REGEX.PASSWORD);
    }
}
