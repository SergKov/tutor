package com.getprepared.controller;

import com.getprepared.controller.factory.ControllerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.REDIRECT;

/**
 * Created by koval on 14.01.2017.
 */
@WebServlet("/")
public class FrontController extends HttpServlet {

    private static final String CONTROLLER_KEY = "controller";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String controllerKey = req.getRequestURI();
        getPage(req, resp, controllerKey);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String controllerKey = req.getParameter(CONTROLLER_KEY);
        getPage(req, resp, controllerKey);
    }

    private void getPage(HttpServletRequest req, HttpServletResponse resp,
                         String controllerKey) throws IOException, ServletException {

        final Controller controller = ControllerFactory.getInstance().getController(controllerKey);

        if (controller == null) {
            resp.sendRedirect(LINKS.NOT_FOUND);
        } else {
            final String page = controller.execute(req, resp);
            if (!REDIRECT.equals(page)) {
                req.getRequestDispatcher(page).forward(req, resp);
            }
        }
    }
}
