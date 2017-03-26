package com.getprepared.web.command.common;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.PageConstants.NAMES.PAGE_NOT_FOUND;
import static com.getprepared.web.constant.PageConstants.PAGES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 17.01.2017.
 */
@Controller
@RequestMapping(LINKS.NOT_FOUND)
public class PageNotFoundCommand implements Command {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
        request.setAttribute(TITLE, messages.getMessage(PAGE_NOT_FOUND, request.getLocale()));
        return PAGES.NOT_FOUND;
    }
}
