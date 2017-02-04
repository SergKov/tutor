package com.getprepared.controller.tutor;

import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.UtilsConstant.VALIDATION;

/**
 * Created by koval on 27.01.2017.
 */
public class QuestionAddPageController extends AbstractQuestionAddController {

    private Validation validation;

    @Override
    public void init() {
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        return fillPage(request, response, validation);
    }
}
