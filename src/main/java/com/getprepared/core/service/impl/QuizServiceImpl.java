package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.persistence.dao.QuizDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koval on 15.01.2017.
 */
@Service("quizService")
public class QuizServiceImpl extends AbstractService implements QuizService {

    @Inject
    private QuizDao quizDao;

    @Inject
    private QuestionService questionService;

    @Inject
    private AnswerService answerService;

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        try {
            transactionManager.begin();
            quizDao.save(quiz);
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final Quiz quiz = quizDao.findById(id);
            final List<Question> questions = questionService.findByQuizId(id);

            questions.forEach(question -> {
                final List<Answer> answers = answerService.findByQuestionId(question.getId());
                question.setAnswers(answers);
            });
            quiz.setQuestions(questions);

            transactionManager.commit();
            return quiz;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Quiz> findAllByTutorId(final Long id, final PageableData page) {
        transactionManager.begin();
        final List<Quiz> quizzes = quizDao.findAllByTutorId(id, page);

        initQuiz(quizzes);

        transactionManager.commit();
        return quizzes;
    }

    @Override
    public List<Quiz> findAllActive(final PageableData page) {
        transactionManager.begin();
        final List<Quiz> createdQuizzes = quizDao.findAllCreated(page);

        initQuiz(createdQuizzes);

        transactionManager.commit();
        return createdQuizzes;
    }

    @Override
    public void active(final Quiz quiz) throws QuizTerminatedException {
        checkActive(quiz);

        transactionManager.begin();
        quizDao.activeQuiz(quiz.getId());
        transactionManager.commit();
    }

    @Override
    public void update(final Quiz quiz) throws QuizTerminatedException, EntityExistsException {
        checkActive(quiz);

        try {
            transactionManager.begin();
            quizDao.update(quiz.getName(), quiz.getId());
            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }

    }

    private void initQuiz(final List<Quiz> quizzes) {
        quizzes.forEach(quiz -> {
            final List<Question> questions = questionService.findByQuizId(quiz.getId());

            questions.forEach(question -> {
                final List<Answer> answers = answerService.findByQuestionId(question.getId());
                question.setAnswers(answers);
            });

            quiz.setQuestions(questions);
        });
    }

    @Override
    public void remove(final Long id) {
        transactionManager.begin();
        quizDao.remove(id);
        transactionManager.commit();
    }
}
