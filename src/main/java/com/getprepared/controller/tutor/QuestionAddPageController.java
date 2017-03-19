package com.getprepared.controller.tutor;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.validation.ValidationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koval on 27.01.2017.
 */
@Component("questionAddPage")
public class QuestionAddPageController extends AbstractQuestionAddController {

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        return fillPage(request, response, validationService);
    }
}
