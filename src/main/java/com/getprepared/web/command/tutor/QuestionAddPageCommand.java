package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.constant.WebConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.LINK;
import static com.getprepared.web.constant.PageConstant.PAGE;
import static com.getprepared.web.constant.PageConstant.REDIRECT;
import static com.getprepared.web.constant.WebConstant.*;

/**
 * Created by koval on 27.01.2017.
 */
@Controller
@CommandMapping(LINK.TUTOR_QUESTION_ADD)
public class QuestionAddPageCommand extends AbstractQuestionAddCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute(SESSION_ATTRIBUTE.QUIZ_ID) == null) {
            response.sendRedirect(LINK.NOT_FOUND);
            return REDIRECT;
        }

        fillPage(request);
        return PAGE.TUTOR_QUESTION_ADD;
    }
}
