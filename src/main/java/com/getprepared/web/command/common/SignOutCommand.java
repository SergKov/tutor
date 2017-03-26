package com.getprepared.web.command.common;

import com.getprepared.web.annotation.RequestMethod;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstants;

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
@Controller
@RequestMapping(LINKS.SIGN_OUT)
public class SignOutCommand implements Command {

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
