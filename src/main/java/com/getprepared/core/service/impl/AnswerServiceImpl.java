package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.domain.Answer;

import java.util.List;

/**
 * Created by koval on 14.01.2017.
 */
@Service("answerService")
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    @Inject
    private AnswerDao answerDao;

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        answerDao.save(answer);
    }

    @Override
    public void save(final List<Answer> answers) throws EntityExistsException {
        answerDao.saveBatch(answers);
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        return answerDao.findById(id);
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        return answerDao.findByQuestionId(questionId);
    }

    @Override
    public List<Answer> findByQuestionIdRandom(final Long questionId) {
        return answerDao.findByQuestionIdRandom(questionId);
    }
}
