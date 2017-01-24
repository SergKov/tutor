package com.getprepared.controller.tutor;

import com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES;
import com.getprepared.controller.AbstractController;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.exception.ParseException;
import com.getprepared.exception.ValidationException;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import com.getprepared.utils.Parser;
import com.getprepared.utils.impl.Messages;
import jdk.nashorn.internal.runtime.ParserException;
import org.apache.log4j.Logger;
import com.getprepared.utils.impl.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.PageConstants.*;
import static com.getprepared.constant.UtilsConstant.PARSER;
import static com.getprepared.constant.WebConstants.*;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.constant.WebConstants.REQUEST_ATTRIBUTES.TITLE;

/**
 * Created by koval on 24.01.2017.
 */
public abstract class AbstractQuizQuestionsController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractQuizQuestionsController.class);

    private final Parser parser = getUtilsFactory().getUtil(PARSER, Parser.class);

    protected void fillPage(final HttpServletRequest request, final QuizService quizService,
                            final QuestionService questionService) {

        try {
            request.setAttribute(TITLE, Messages.getInstance().getMessage(NAMES.QUESTIONS, request.getLocale()));

            final Object quizIdObject = request.getSession().getAttribute(SESSION_ATTRIBUTES.QUIZ_ID);
            final Long quizId = parser.parseLong(quizIdObject);
            final Quiz quiz = quizService.findById(quizId);
            request.setAttribute(REQUEST_ATTRIBUTES.QUIZ, quiz);
            final List<Question> questions = questionService.findByQuizId(quizId);
            if (!CollectionUtils.isEmpty(questions)) {
                request.setAttribute(REQUEST_ATTRIBUTES.QUESTIONS, questions);
            }
        } catch (final EntityNotFoundException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.QUIZ_NOT_FOUND, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        } catch (ValidationException | ParseException e) {
            request.setAttribute(ERROR_MSG, getMessages().getMessage(ERRORS.INVALIDATED_ID, request.getLocale()));
            LOG.warn(e.getMessage(), e);
        }
    }
}
