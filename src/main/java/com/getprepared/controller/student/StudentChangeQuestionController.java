package com.getprepared.controller.student;

import com.getprepared.constant.PageConstants;
import com.getprepared.constant.WebConstants;
import com.getprepared.controller.AbstractController;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.domain.Question;
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
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 30.01.2017.
 */
public class StudentChangeQuestionController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(StudentChangeQuestionController.class);

    private static final int FIRST_QUESTION = 0;

    @Override
    public void init() { }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, getMessages().getMessage(PageConstants.NAMES.TEST, request.getLocale()));

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);

        Question question = null;
        try {
            final Integer questionNumber = Integer.valueOf(request.getParameter(INPUTS.QUESTION_NUMBER));
            if (questionNumber <= test.size() && questionNumber > 0) {
                question = test.get(questionNumber - 1).getQuestion();
            }
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
        }

        request.setAttribute(QUESTION, question == null ? test.get(FIRST_QUESTION).getQuestion() : question);

        return PAGES.STUDENT_TEST;
    }
}
