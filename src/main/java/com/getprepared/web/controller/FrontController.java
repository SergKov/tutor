package com.getprepared.web.controller;

import com.getprepared.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.context.Registry.getWebContext;
import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.REDIRECT;

/**
 * Created by koval on 14.01.2017.
 */
@WebServlet("/")
public class FrontController extends HttpServlet {

    private static final String CONTROLLER_KEY = "controller";

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        final String controllerKey = req.getRequestURI();
        getPage(req, resp, controllerKey);
    }

    @Override
    public void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException, ServletException {
        final String controllerKey = req.getParameter(CONTROLLER_KEY);
        getPage(req, resp, controllerKey);
    }

    private void getPage(final HttpServletRequest req, final HttpServletResponse resp,
                         final String commandKey) throws IOException, ServletException {

        final Command command = getWebContext().getCommand(commandKey);

        if (command == null) {
            resp.sendRedirect(LINKS.NOT_FOUND);
        } else {
            final String page = command.execute(req, resp);
            if (!REDIRECT.equals(page)) {
                req.getRequestDispatcher(page).forward(req, resp);
            }
        }
    }
}
