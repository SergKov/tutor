package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.annotation.Service;
import com.getprepared.core.annotation.Transactional;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.ResultService;
import com.getprepared.core.service.UserService;
import com.getprepared.persistence.dao.ResultDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Result;
import com.getprepared.persistence.domain.User;

import java.util.List;

import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_NUMBER_OF_ELEMENTS;
import static com.getprepared.web.constant.ApplicationConstant.DEFAULT_PAGE_NUMBER;

/**
 * Created by koval on 15.01.2017.
 */
@Service("resultService")
public class ResultServiceImpl extends AbstractService implements ResultService {

    @Inject
    private ResultDao resultDao;

    @Inject
    private UserService userService;

    @Inject
    private QuizService quizService;

    @Override
    public void save(final Result result) throws EntityExistsException {
        resultDao.save(result);
    }

    @Override
    @Transactional
    public List<Result> findByUserId(final Long id, final PageableData page) {
        page.setNumberOfElements(resultDao.countFoundRows());
        checkPage(page);
        final List<Result> results = resultDao.findByUserId(id, page);

        iniResult(results);
        return results;
    }

    private void iniResult(final List<Result> results) {
        for (final Result result : results) {
            try {
                final User user = userService.findById(result.getId());
                result.setUser(user);

                final Quiz quiz = quizService.findById(user.getId());
                result.setQuiz(quiz);
            } catch (final EntityNotFoundException e) { /* ignore, unreal situation */
            }
        }
    }
}