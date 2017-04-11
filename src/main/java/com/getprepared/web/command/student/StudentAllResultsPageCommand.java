package com.getprepared.web.command.student;

import com.getprepared.annotation.Inject;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.util.Messages;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;
import com.getprepared.web.annotation.CommandMapping;
import com.getprepared.web.annotation.Controller;
import com.getprepared.web.command.AbstractPageableCommand;
import com.getprepared.web.constant.WebConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.LINK;
import static com.getprepared.web.constant.ApplicationConstant.PATH;
import static com.getprepared.web.constant.PropertyConstant.KEY.ALL_RESULTS;
import static com.getprepared.web.constant.WebConstant.*;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.PAGINATION;
import static com.getprepared.web.constant.WebConstant.REQUEST_ATTRIBUTE.TITLE;
import static com.getprepared.web.constant.WebConstant.SESSION_ATTRIBUTE;

/**
 * Created by koval on 09.04.2017.
 */
@Controller
@CommandMapping(LINK.STUDENT_RESULTS)
public class StudentAllResultsPageCommand extends AbstractPageableCommand {

    private static final String RESULTS = "results";

    @Inject
    private ResultService resultService;

    @Inject
    private Messages messages;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        request.setAttribute(TITLE, messages.getMessage(ALL_RESULTS, request.getLocale()));

        final PageableData pagination = doPageable(request, RESULTS);

        final Long id = ((User)request.getSession().getAttribute(SESSION_ATTRIBUTE.STUDENT)).getId();

        final List<Result> results = resultService.findByUserId(id, pagination);
        request.setAttribute(REQUEST_ATTRIBUTE.RESULTS, results);

        return PATH.STUDENT_RESULTS;
    }
}
