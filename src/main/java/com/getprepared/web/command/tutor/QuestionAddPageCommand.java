package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Created by koval on 27.01.2017.
 */
@Controller
@CommandMapping(LINK.TUTOR_QUESTION_ADD)
public class QuestionAddPageCommand extends AbstractQuestionAddCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTE.QUIZ_ID) == null) {
            response.sendError(SC_NOT_FOUND);
            return REDIRECT;
        }

        fillPage(request);
        return PATH.TUTOR_QUESTION_ADD;
    }
}
