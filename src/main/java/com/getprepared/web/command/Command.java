package com.getprepared.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koval on 14.01.2017.
 */
public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
