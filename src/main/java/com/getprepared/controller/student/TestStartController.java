package com.getprepared.controller.student;

import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.service.QuestionService;
import com.getprepared.utils.Validation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;
import static com.getprepared.constant.UtilsConstant.VALIDATION;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 30.01.2017.
 */
public class TestStartController extends AbstractTestController {

    private static final Logger LOG = Logger.getLogger(TestStartController.class);

    private QuestionService questionService;
    private Validation validation;

    @Override
    public void init() {
        questionService = getServiceFactory().getService(QUESTION_SERVICE, QuestionService.class);
        validation = getUtilsFactory().getUtil(VALIDATION, Validation.class);
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        fillPage(request);

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);

        Integer questionNumber = (Integer) request.getAttribute(INPUTS.QUESTION_NUMBER);
        if (questionNumber == null || questionNumber > test.size() || questionNumber < 0) {
            questionNumber = 1;
        }

        request.setAttribute(QUESTION, test.get(questionNumber - 1).getQuestion());

        return PAGES.STUDENT_TEST;
    }
}
