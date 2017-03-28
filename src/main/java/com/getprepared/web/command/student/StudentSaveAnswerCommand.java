package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;
import com.getprepared.web.dto.TestQuestion;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static org.apache.commons.lang3.ArrayUtils.contains;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

/**
 * Created by koval on 05.02.2017.
 */
@Controller
@CommandMapping(COMMAND.STUDENT_ANSWER_SAVE)
public class StudentSaveAnswerCommand implements Command {

    private static final Logger LOG = Logger.getLogger(StudentSaveAnswerCommand.class);

    private static final Integer FIRST_QUESTION = 1;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String[] chosenAnswersId = request.getParameterValues(INPUT.CHOSEN_ANSWER_ID);

        @SuppressWarnings("unchecked")
        final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTE.TEST);

        Integer questionNumber = null;
        try {
            questionNumber = Integer.valueOf(request.getParameter(INPUT.QUESTION_NUMBER));
            if (questionNumber <= test.size() && questionNumber > 0) {
                final TestQuestion testQuestion = test.get(questionNumber - 1);

                final List<Answer> chosenAnswers = new ArrayList<>();

                if (isNotEmpty(chosenAnswersId)) {
                    testQuestion.getQuestion().getAnswers().stream()
                            .filter(answer -> contains(chosenAnswersId, String.valueOf(answer.getId())))
                            .forEach(chosenAnswers::add);
                }
                testQuestion.setAnswers(chosenAnswers);
            }
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage());
            questionNumber = FIRST_QUESTION;
        }

        request.setAttribute(TEST_QUESTION, test.get(questionNumber - 1));
        request.setAttribute(CURRENT_QUESTION, questionNumber);
        request.setAttribute(TITLE, messages.getMessage(PageConstant.TITLE.TEST, request.getLocale()));

        return PAGE.STUDENT_TEST;
    }
}
