package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 27.01.2017.
 */
@Controller
@RequestMapping(LINKS.TUTOR_QUESTION_ADD)
public class QuestionAddPageCommand extends AbstractQuestionAddCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_QUESTION_ADD;
    }
}
