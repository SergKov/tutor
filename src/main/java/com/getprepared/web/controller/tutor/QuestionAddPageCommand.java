package com.getprepared.web.controller.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.validation.ValidationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koval on 27.01.2017.
 */
@Controller
public class QuestionAddPageCommand extends AbstractQuestionAddCommand {

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        return returnPage(request);
    }
}
