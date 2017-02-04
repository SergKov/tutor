package com.getprepared.controller.tutor;

import com.getprepared.exception.ValidationException;
import com.getprepared.utils.Validation;
import com.getprepared.utils.impl.ValidationImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.LINKS;
import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.PageConstants.REDIRECT;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;

/**
 * Created by koval on 27.01.2017.
 */
public class QuestionAddPageController extends AbstractQuestionAddController {

    private static final Logger LOG = Logger.getLogger(QuestionAddPageController.class);

    private Validation validation;

    @Override
    public void init() {
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return fillPage(request, response, validation);
    }
}
