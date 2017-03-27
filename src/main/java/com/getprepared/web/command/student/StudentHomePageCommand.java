package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.QuizService;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.annotation.CommandMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.web.constant.PageConstants.LINKS;
import static com.getprepared.web.constant.PageConstants.PAGES;

/**
 * Created by koval on 21.01.2017.
 */
@Controller
@CommandMapping(LINKS.STUDENT_HOME_PAGE)
public class StudentHomePageCommand extends AbstractStudentHomePageCommand {

    @Inject
    private QuizService quizService;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        fillPage(request, quizService);
        return PAGES.STUDENT_HOME_PAGE;
    }
}
