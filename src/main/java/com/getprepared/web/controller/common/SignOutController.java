package com.getprepared.web.controller.common;

import com.getprepared.annotation.Component;
import com.getprepared.web.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.REDIRECT;
import static com.getprepared.web.constant.WebConstants.SESSION_ATTRIBUTES.TUTOR;

/**
 * Created by koval on 22.01.2017.
 */
@Component("signOut")
public class SignOutController implements Controller {

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
