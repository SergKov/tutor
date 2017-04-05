package com.getprepared.web.command.common;

import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.getprepared.web.constant.ApplicationConstant.LINK;
import static com.getprepared.web.constant.ApplicationConstant.REDIRECT;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE.TUTOR;

/**
 * Created by koval on 22.01.2017.
 */
@Controller
@CommandMapping(LINK.SIGN_OUT)
public class SignOutCommand implements Command {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Optional<HttpSession> httpSession = Optional.ofNullable(request.getSession(false));

        if (httpSession.isPresent() && httpSession.get().getAttribute(TUTOR) != null) {
            response.sendRedirect(LINK.TUTOR_SIGN_IN);
        } else {
            response.sendRedirect(LINK.STUDENT_SIGN_IN);
        }

        httpSession.ifPresent(HttpSession::invalidate);
        return REDIRECT;
    }
}
