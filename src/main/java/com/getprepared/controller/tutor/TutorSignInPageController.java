package com.getprepared.controller.tutor;

import com.getprepared.controller.common.AbstractSignInController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 21.01.2017.
 */
public class TutorSignInPageController extends AbstractSignInController {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_SIGN_IN;
    }
}
