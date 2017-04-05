package com.getprepared.web.command.tutor;

import com.getprepared.annotation.Inject;
import com.getprepared.core.util.Messages;
import com.getprepared.web.command.Command;

import javax.servlet.http.HttpServletRequest;

import static com.getprepared.web.constant.PropertyConstant.KEY.ADD_QUIZ;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;

/**
 * Created by koval on 22.01.2017.
 */
public abstract class AbstractQuizAddCommand implements Command {

    @Inject
    private Messages messages;

    protected void fillPage(final HttpServletRequest request) {
        request.setAttribute(TITLE, messages.getMessage(ADD_QUIZ, request.getLocale()));
    }
}
