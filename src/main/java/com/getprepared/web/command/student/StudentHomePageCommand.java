package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.ApplicationConstant.LINK;
import static com.getprepared.web.constant.ApplicationConstant.PATH;

/**
 * Created by koval on 21.01.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_HOME_PAGE)
public class StudentHomePageCommand extends AbstractStudentHomePageCommand {

    @Inject
    private QuizService quizService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request, quizService);
        return PATH.STUDENT_HOME_PAGE;
    }
}
