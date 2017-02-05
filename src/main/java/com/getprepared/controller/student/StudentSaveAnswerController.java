package com.getprepared.controller.student;

import com.getprepared.controller.AbstractController;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.QUESTION;
import static com.getprepared.constant.WebConstants.SESSION_ATTRIBUTES;

/**
 * Created by koval on 05.02.2017.
 */
public class StudentSaveAnswerController extends AbstractTestController {

    private static final Logger LOG = Logger.getLogger(StudentSaveAnswerController.class);

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String[] answerTypes = request.getParameterValues(INPUTS.ANSWER_TYPE);

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTES.TEST);

        Question question = null;
        try {
            final Integer questionNumber = Integer.valueOf(request.getParameter(INPUTS.QUESTION_NUMBER));
            if (questionNumber <= test.size() && questionNumber > 0) {
                question = test.get(questionNumber - 1).getQuestion();

                final TestQuestion testQuestion = test.get(questionNumber);
                final List<Answer> answers = testQuestion.getQuestion().getAnswers();

                for (int i = 0; i < answerTypes.length; i++) {
                    if (answerTypes[i].equals(AnswerType.CORRECT.name())) {
                        testQuestion.getAnswers().add(answers.get(i));
                    }
                }
            }
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
        }

        request.setAttribute(QUESTION, question == null ? test.get(FIRST_QUESTION).getQuestion() : question);

        return PAGES.STUDENT_TEST;
    }
}
