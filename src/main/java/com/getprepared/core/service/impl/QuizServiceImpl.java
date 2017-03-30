package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.persistence.dao.QuizDao;
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
    public List<Quiz> findAll()  {
        transactionManager.begin();
        final List<Quiz> quizzes = quizDao.findAll();

        quizzes.forEach(quiz -> {
            final List<Question> questions = questionService.findByQuizId(quiz.getId());

            questions.forEach(question -> {
                final List<Answer> answers = answerService.findByQuestionId(question.getId());
                question.setAnswers(answers);
            });

            quiz.setQuestions(questions);
        });
        transactionManager.commit();
        return quizzes;
    }

    @Override //TODO
    public List<Quiz> findAllCreated() {
        transactionManager.begin();
        final List<Quiz> allQuizzes = findAll();
        final List<Quiz> createdQuizzes = new ArrayList<>();

        allQuizzes.forEach(quiz -> {
            if (CollectionUtils.isNotEmpty(quiz.getQuestions()) && quiz.getQuestions().size() >= 2) {
                createdQuizzes.add(quiz);
            }
        });
        transactionManager.commit();
        return createdQuizzes;
    }

    @Override
    public void remove(final Quiz quiz) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            quizDao.remove(quiz.getId());
            transactionManager.commit();
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }
}
