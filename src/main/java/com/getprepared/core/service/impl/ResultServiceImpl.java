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
    public List<Result> findByUserId(final Long id, final PageableData page) throws EntityNotFoundException {
        final List<Result> results = resultDao.findByUserId(id, page);

        page.setNumberOfElements(resultDao.countFoundRows());

        for (final Result result : results) {
            final User user = userService.findById(result.getId());
            result.setUser(user);

            final Quiz quiz = quizService.findById(user.getId());
            result.setQuiz(quiz);
        }
        return results;
    }
}