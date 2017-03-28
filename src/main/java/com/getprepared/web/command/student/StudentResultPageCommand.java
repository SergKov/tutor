package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;

/**
 * Created by koval on 06.02.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_RESULT)
public class StudentResultPageCommand implements Command {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTE.MARK) == null) {
            response.sendRedirect(LINK.NOT_FOUND);
            return REDIRECT;
        }

        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.RESULT, request.getLocale()));
        request.getSession().getAttribute(SESSION_ATTRIBUTE.MARK);
        return PATH.STUDENT_RESULT;
    }
}
