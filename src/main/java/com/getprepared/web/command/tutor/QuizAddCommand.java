package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.converter.Converter;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.form.QuizAddForm;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.getprepared.web.constant.PageConstants.*;
import static com.getprepared.web.constant.WebConstants.INPUTS;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSG;
import static com.getprepared.web.constant.WebConstants.REQUEST_ATTRIBUTES.ERROR_MSGS;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

/**
 * Created by koval on 22.01.2017.
 */
@Controller
public class QuizAddCommand extends AbstractQuizAddCommand {

    private static final Logger LOG = Logger.getLogger(QuizAddCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private ValidationService validationService;

    @Inject
    private Converter<QuizAddForm, Quiz> converter;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final QuizAddForm quizForm = new QuizAddForm();

        final String quizName = request.getParameter(INPUTS.QUIZ_NAME);
        quizForm.setName(quizName);

        final Map<String, String> errors = validationService.validate(quizForm);
        if (isNotEmpty(errors)) {
            request.setAttribute(ERROR_MSGS, messages.getMessages(errors, request.getLocale()));
        } else {
            final Quiz quiz = converter.convert(quizForm);
            try {
                quizService.save(quiz);
                response.sendRedirect(LINKS.TUTOR_QUIZZES);
                return REDIRECT;
            } catch (final EntityExistsException e) {
                LOG.warn(e.getMessage(), e);
                request.setAttribute(ERROR_MSG, messages.getMessage(ERRORS.QUIZ_EXISTS, request.getLocale()));
            }
        }

        request.setAttribute(REQUEST_ATTRIBUTES.QUIZ, quizForm);
        fillPage(request);
        return PAGES.TUTOR_ADD_QUIZ;
    }
}
