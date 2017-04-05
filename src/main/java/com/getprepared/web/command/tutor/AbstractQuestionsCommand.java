package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.web.constant.PropertyConstant.KEY.QUESTIONS;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 25.01.2017.
 */
public abstract class AbstractQuestionsCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService,
                            final QuestionService questionService) throws EntityNotFoundException {

        request.setAttribute(TITLE, messages.getMessage(QUESTIONS, request.getLocale()));

        final Long quizId = (Long) request.getSession().getAttribute(INPUT.QUIZ_ID);
        final Quiz quiz = quizService.findById(quizId);
        request.setAttribute(REQUEST_ATTRIBUTE.QUIZ, quiz);

        final List<Question> questions = questionService.findByQuizId(quizId);
        if (!CollectionUtils.isEmpty(questions)) {
            request.setAttribute(REQUEST_ATTRIBUTE.QUESTIONS, questions);
        }
    }
}
