package com.getprepared.controller.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import com.getprepared.controller.Controller;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.util.Messages;
import com.getprepared.validation.ValidationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.PageConstants.NAMES;
import static com.getprepared.constant.WebConstants.INPUTS;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 25.01.2017.
 */
public abstract class AbstractQuestionsController implements Controller {

    private static final Logger LOG = Logger.getLogger(AbstractQuestionsController.class);

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request, final QuizService quizService,
                            final QuestionService questionService,
                            final ValidationService validationService)  {

        request.setAttribute(TITLE, messages.getMessage(NAMES.QUESTIONS, request.getLocale()));

        final Object quizId = request.getSession().getAttribute(INPUTS.QUIZ_ID);

        try {
            final Long parsedQuizId = (Long) quizId;
            // TODO add validation

            final Quiz quiz = quizService.findById(parsedQuizId);
            request.setAttribute(REQUEST_ATTRIBUTES.QUIZ, quiz);

            final List<Question> questions = questionService.findByQuizId(parsedQuizId);
            if (!CollectionUtils.isEmpty(questions)) {
                request.setAttribute(REQUEST_ATTRIBUTES.QUESTIONS, questions);
            }
        } catch (EntityNotFoundException | ClassCastException e) {
            LOG.warn(e.getMessage(), e);
//            throw new ValidationException(String.format("Illegal quizId %s", quizId), e);
        }
    }
}
