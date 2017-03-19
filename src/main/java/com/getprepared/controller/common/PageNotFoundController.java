package com.getprepared.controller.common;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.Controller;
import com.getprepared.util.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.constant.PageConstants.NAMES.PAGE_NOT_FOUND;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 17.01.2017.
 */
@Component("pageNotFound")
public class PageNotFoundController implements Controller {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
        request.setAttribute(TITLE, messages.getMessage(PAGE_NOT_FOUND, request.getLocale()));
        return PAGES.NOT_FOUND;
    }
}
