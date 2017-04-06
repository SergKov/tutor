package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.persistence.dao.AnswerDao;
import com.getprepared.persistence.domain.Answer;

import java.util.List;
import java.util.Objects;

/**
 * Created by koval on 14.01.2017.
 */
@Service("answerService")
public class AnswerServiceImpl extends AbstractService implements AnswerService {

    @Inject
    private AnswerDao answerDao;

    @Override
    public void save(final Answer answer) throws EntityExistsException {
        Objects.requireNonNull(answer, "Answer can not be null");
        answerDao.save(answer);
    }

    @Override
    public void save(final List<Answer> answers) throws EntityExistsException {
        Objects.requireNonNull(answers, "Answers can not be null");
        answerDao.saveBatch(answers);
    }

    @Override
    public Answer findById(final Long id) throws EntityNotFoundException {
        Objects.requireNonNull(id, "id can not be null");
        return answerDao.findById(id);
    }

    @Override
    public List<Answer> findByQuestionId(final Long questionId) {
        Objects.requireNonNull(questionId, "id can not be null");
        return answerDao.findByQuestionId(questionId);
    }

    @Override
    public List<Answer> findByQuestionIdInRandomOrder(final Long questionId) {
        Objects.requireNonNull(questionId, "id can not be null");
        return answerDao.findByQuestionIdInRandomOrder(questionId);
    }
}
