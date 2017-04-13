package com.getprepared.web.command.student;

import com.getprepared.web.command.Command;
import com.getprepared.web.dto.TestQuestion;
import org.apache.log4j.Logger;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by koval on 13.04.2017.
 */
public abstract class AbstractTestCommand implements Command {

    protected static final Integer FIRST_QUESTION = 1;

    private static final Logger LOG = Logger.getLogger(AbstractTestCommand.class);

    protected Integer parseQuestionNumber(final String number, final List<TestQuestion> test) {
        if (isNumeric(number)) {
            try {
                Integer parsedQuestionNumber = Integer.valueOf(number);
                if (parsedQuestionNumber > test.size() || parsedQuestionNumber <= 0) {
                    parsedQuestionNumber = FIRST_QUESTION;
                }
                return parsedQuestionNumber;
            } catch (final NumberFormatException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return FIRST_QUESTION;
    }
}
