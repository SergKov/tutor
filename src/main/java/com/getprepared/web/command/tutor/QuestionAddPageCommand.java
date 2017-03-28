package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.LINK;
import static com.getprepared.web.constant.PageConstant.PATH;

/**
 * Created by koval on 27.01.2017.
 */
@Controller
@CommandMapping(LINK.TUTOR_QUESTION_ADD)
public class QuestionAddPageCommand extends AbstractQuestionAddCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PATH.TUTOR_QUESTION_ADD;
    }
}
