package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.web.annotation.CommandMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 22.01.2017.
 */
@Component
@CommandMapping(LINKS.TUTOR_QUIZ_ADD)
public class QuizPageAddCommand extends AbstractQuizAddCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_QUIZ_ADD;
    }
}
