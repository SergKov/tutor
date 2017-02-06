package com.getprepared.controller.student;

import com.getprepared.constant.WebConstants;
import com.getprepared.controller.AbstractController;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.domain.Question;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.getprepared.constant.WebConstants.*;

/**
 * Created by koval on 05.02.2017.
 */
public abstract class AbstractTestController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractTestController.class);

    protected static final int FIRST_QUESTION = 1;

    protected Integer getQuestionNumber(final HttpServletRequest request, final List<TestQuestion> test) {
        try {
            final Integer questionNumber = Integer.valueOf(request.getParameter(INPUTS.QUESTION_NUMBER));
            if (questionNumber <= test.size() && questionNumber > 0) {
                return questionNumber;
            }
        } catch (final NumberFormatException e) {
            LOG.warn(e.getMessage());
        }

        return FIRST_QUESTION;
    }
}
