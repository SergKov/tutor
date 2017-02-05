package com.getprepared.controller.common;

import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES.STUDENT;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES.TUTOR;

/**
 * Created by koval on 22.01.2017.
 */
public class SignOutController extends AbstractController {

    @Override
    public void init() { }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Optional<HttpSession> httpSession = Optional.ofNullable(request.getSession(false));

        if (httpSession.isPresent() && httpSession.get().getAttribute(TUTOR) != null) {
            response.sendRedirect(LINKS.TUTOR_SIGN_IN);
        } else {
            response.sendRedirect(LINKS.STUDENT_SIGN_IN);
        }

        httpSession.ifPresent(HttpSession::invalidate);
        return REDIRECT;
    }
}
