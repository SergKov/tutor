package com.getprepared.controller.student;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;

/**
 * Created by koval on 30.01.2017.
 */
public class TestPageController extends AbstractTestController {

    @Override
    public void init() { }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request, response);
        return PAGES.TEST;
    }
}
