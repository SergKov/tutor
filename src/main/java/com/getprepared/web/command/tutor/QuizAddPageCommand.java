package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 22.01.2017.
 */
@Controller
@CommandMapping(LINKS.TUTOR_QUIZ_ADD)
public class QuizAddPageCommand extends AbstractQuizAddCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_QUIZ_ADD;
    }
}
