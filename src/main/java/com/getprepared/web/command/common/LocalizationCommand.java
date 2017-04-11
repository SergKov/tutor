package com.getprepared.web.command.common;

import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.COMMAND;
import static com.getprepared.web.constant.ApplicationConstant.REDIRECT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.LANGUAGE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;

/**
 * Created by koval on 11.04.2017.
 */
@Controller
@CommandMapping(COMMAND.LANGUAGE)
public class LocalizationCommand implements Command {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String language = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(SESSION_ATTRIBUTE.LANGUAGE, language);
        response.sendRedirect(request.getRequestURI());
        return REDIRECT;
    }
}
