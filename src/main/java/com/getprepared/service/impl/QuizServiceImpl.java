package com.getprepared.service.impl;

import com.getprepared.annotation.Component;
import com.getprepared.annotation.Inject;
import com.getprepared.dao.QuizDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;
import com.getprepared.domain.Quiz;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;
import com.getprepared.service.QuizService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koval on 15.01.2017.
 */
@Component("quizService")
public class QuizServiceImpl extends AbstractService implements QuizService {

    @Inject
    private QuizDao quizDao;

    @Inject
    private QuestionService questionService;

    @Inject
    private AnswerService answerService;

    public QuizServiceImpl() { }

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        try {
            quizDao.save(quiz);
        } catch (final EntityExistsException e) {
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

            transactionManager.rollback();
            return quiz;
        } catch (final EntityNotFoundException e) {
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
        transactionManager.rollback();
        return quizzes;
    }

    @Override //TODO
    public List<Quiz> findAllCreated() {
        final List<Quiz> allQuizzes = findAll();
        final List<Quiz> createdQuizzes = new ArrayList<>();

        allQuizzes.forEach(quiz -> {
            if (CollectionUtils.isNotEmpty(quiz.getQuestions()) && quiz.getQuestions().size() >= 2) {
                createdQuizzes.add(quiz);
            }
        });
        return createdQuizzes;
    }

    @Override
    public void remove(final Quiz quiz) throws EntityNotFoundException {
        try {
            quizDao.remove(quiz.getId());
        } catch (final EntityNotFoundException e) {
            throw e;
        }
    }
}
