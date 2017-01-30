package com.getprepared.controller.student;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.PageConstants.REDIRECT;

/**
 * Created by koval on 30.01.2017.
 */
public class TestPageController extends AbstractTestController {

    private static final Logger LOG = Logger.getLogger(TestPageController.class);

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            fillPage(request);
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
            response.sendRedirect(PAGES.NOT_FOUND);
            return REDIRECT;
        }

        return PAGES.TEST;
    }
}
