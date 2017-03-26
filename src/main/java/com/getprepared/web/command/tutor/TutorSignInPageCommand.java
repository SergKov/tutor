package com.getprepared.web.command.tutor;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.RequestMapping;
import com.getprepared.web.command.common.AbstractSignInCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 21.01.2017.
 */
@Controller
@RequestMapping(LINKS.TUTOR_SIGN_IN)
public class TutorSignInPageCommand extends AbstractSignInCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PAGES.TUTOR_SIGN_IN;
    }
}
