package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.dto.TestQuestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.COMMAND;
import static com.getprepared.web.constant.ApplicationConstant.PATH;
import static com.getprepared.web.constant.PropertyConstant.KEY.TEST;
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
public class StudentSaveAnswerCommand extends AbstractTestCommand {

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String[] chosenAnswersId = request.getParameterValues(INPUT.CHOSEN_ANSWER_ID);

        @SuppressWarnings("unchecked") final List<TestQuestion> test = (List<TestQuestion>) request.getSession().getAttribute(SESSION_ATTRIBUTE.TEST);

        Integer parsedQuestionNumber = parseQuestionNumber(request.getParameter(INPUT.QUESTION_NUMBER), test);

        final TestQuestion testQuestion = test.get(parsedQuestionNumber - 1);

        final List<Answer> chosenAnswers = new ArrayList<>();

        if (isNotEmpty(chosenAnswersId)) {
            testQuestion.getQuestion().getAnswers().stream()
                    .filter(answer -> contains(chosenAnswersId, String.valueOf(answer.getId())))
                    .forEach(chosenAnswers::add);
        }

        testQuestion.setAnswers(chosenAnswers);

        request.setAttribute(TEST_QUESTION, test.get(parsedQuestionNumber - 1));
        request.setAttribute(CURRENT_QUESTION, parsedQuestionNumber);
        request.setAttribute(TITLE, messages.getMessage(TEST, request.getLocale()));

        return PATH.STUDENT_TEST;
    }
}
