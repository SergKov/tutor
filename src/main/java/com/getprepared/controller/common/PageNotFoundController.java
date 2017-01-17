package com.getprepared.controller.common;

import com.getprepared.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.constant.PageConstants.NAMES.PAGE_NOT_FOUND;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 17.01.2017.
 */
public class PageNotFoundController extends AbstractController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(TITLE, PAGE_NOT_FOUND);
        return PAGES.NOT_FOUND;
    }
}
