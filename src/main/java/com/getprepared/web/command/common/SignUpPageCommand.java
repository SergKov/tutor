package com.getprepared.web.command.common;

import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.constant.PageConstant.LINK;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstant.PATH;

/**
 * Created by koval on 17.01.2017.
 */
@Controller
@CommandMapping(LINK.SIGN_UP)
public class SignUpPageCommand extends AbstractSignUpCommand {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request);
        return PATH.SIGN_UP;
    }
}
