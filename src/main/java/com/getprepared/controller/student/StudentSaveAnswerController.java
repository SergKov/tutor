package com.getprepared.controller.student;

import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.domain.Answer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TEST_QUESTION;

/**
 * Created by koval on 05.02.2017.
 */
public class StudentSaveAnswerController extends AbstractTestController {

    private static final Logger LOG = Logger.getLogger(StudentSaveAnswerController.class);

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String[] chosenAnswersId = request.getParameterValues(INPUTS.CHOSEN_ANSWER_ID);

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);

        Integer questionNumber = null;
        try {
            questionNumber = Integer.valueOf(request.getParameter(INPUTS.QUESTION_NUMBER));
            if (questionNumber <= test.size() && questionNumber > 0) {
                final TestQuestion testQuestion = test.get(questionNumber - 1);

                final List<Answer> chosenAnswers = new ArrayList<>();

                if (chosenAnswersId.length > 0) {
                    testQuestion.getQuestion().getAnswers().forEach(answer -> {
                        final String stringAnswerId = String.valueOf(answer.getId());
                        if (ArrayUtils.contains(chosenAnswersId, stringAnswerId)) {
                            chosenAnswers.add(answer);
                        }
                    });
                }

                testQuestion.setAnswers(chosenAnswers);
            }
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage());
            questionNumber = FIRST_QUESTION;
        }

        request.setAttribute(TEST_QUESTION, test.get(questionNumber - 1));
        request.setAttribute(REQUEST_ATTRIBUTES.CURRENT_QUESTION, questionNumber);

        return PAGES.STUDENT_TEST;
    }
}
