package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.converter.Converter;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizNotTerminatedException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.Command;
import com.getprepared.web.constant.PageConstant;
import com.getprepared.web.form.QuizUpdateForm;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

/**
 * Created by koval on 01.04.2017.
 */
@Controller
@CommandMapping(COMMAND.TUTOR_QUIZ_UPDATE)
public class QuizUpdateCommand implements Command {

    private static final Logger LOG = Logger.getLogger(QuizUpdateCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private Converter<QuizUpdateForm, Quiz> quizUpdateConverter;

    @Inject
    private ValidationService validationService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final QuizUpdateForm quizForm = new QuizUpdateForm();

        final String quizName = request.getParameter(INPUT.QUIZ_NAME);
        quizForm.setName(quizName);

        final Map<String, String> errors = validationService.validate(quizForm);
        if (isNotEmpty(errors)) {
            request.setAttribute(ERROR_MSGS, messages.getMessages(errors, request.getLocale()));
        } else {
            final Quiz quiz = quizUpdateConverter.convert(quizForm);
            try {
                quiz.setId(Long.valueOf(request.getParameter(INPUT.QUIZ_ID))); // TODO ???
                quizService.update(quiz);
                response.sendRedirect(LINK.TUTOR_QUIZZES);
                return REDIRECT;
            } catch (final EntityExistsException e) {
                LOG.warn(e.getMessage(), e);
                request.setAttribute(ERROR_MSG, messages.getMessage(ERROR.QUIZ_EXISTS, request.getLocale()));
            } catch (QuizTerminatedException | EntityNotFoundException | NumberFormatException e) {
                LOG.error(e.getMessage(), e);
                response.sendError(SC_NOT_FOUND);
            }
        }

        request.setAttribute(QUIZ, quizForm);
        request.setAttribute(QUIZ_NAME_REGEX, QUIZ_NAME_REGEX);
        request.setAttribute(TITLE, PageConstant.TITLE.QUIZZES);
        return PAGE.TUTOR_QUIZZES;
    }
}
