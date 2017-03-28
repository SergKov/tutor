package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.web.constant.PageConstant.LINK;
import static com.getprepared.web.constant.PageConstant.TITLE.PAGE_NOT_FOUND;
import static com.getprepared.web.constant.PageConstant.PAGE;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 17.01.2017.
 */
@Controller
@CommandMapping(LINK.NOT_FOUND)
public class PageNotFoundCommand implements Command {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
        request.setAttribute(TITLE, messages.getMessage(PAGE_NOT_FOUND, request.getLocale()));
        return PAGE.NOT_FOUND;
    }
}
