package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.command.common.AbstractQuizCommand;
import com.getprepared.web.validation.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.*;
import static com.getprepared.web.constant.WebConstant.INPUT.QUIZ_ID;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 22.01.2017.
 */
@Controller
@CommandMapping(LINK.TUTOR_QUIZZES)
public class QuizPageCommand extends AbstractQuizCommand {

    private static final Logger LOG = Logger.getLogger(QuizPageCommand.class);

    @Inject
    private QuizService quizService;

    @Inject
    private ValidationService validationService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        final String quizId = request.getParameter(QUIZ_ID);

        if (isEmpty(quizId)) {
            request.getSession().removeAttribute(SESSION_ATTRIBUTE.QUIZ_ID);
            fillPage(request, quizService);
            return PATH.TUTOR_QUIZZES;
        }

        if (isNumeric(quizId)) {
            final Long parsedQuizId = Long.valueOf(quizId);
            request.getSession().setAttribute(SESSION_ATTRIBUTE.QUIZ_ID, parsedQuizId);
            response.sendRedirect(LINK.TUTOR_QUESTIONS);
            return REDIRECT;
        }

        response.sendRedirect(LINK.NOT_FOUND);
        return REDIRECT;
    }
}
