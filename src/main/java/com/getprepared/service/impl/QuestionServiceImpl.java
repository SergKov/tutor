package com.getprepared.service.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.annotation.Inject;
import com.getprepared.controller.dto.TestQuestion;
import com.getprepared.dao.QuestionDao;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import com.getprepared.exception.EntityNotFoundException;
import com.getprepared.service.AnswerService;
import com.getprepared.service.QuestionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by koval on 14.01.2017.
 */
@Bean("questionService")
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    @Inject
    private QuestionDao questionDao;

    @Inject
    private AnswerService answerService;

    public QuestionServiceImpl() { }

    @Override
    public void save(final Question question) throws EntityExistsException {
        try {
            transactionManager.begin();
            questionDao.save(question);

            final List<Answer> answers = question.getAnswers();
            answerService.save(answers);

            transactionManager.commit();
        } catch (final EntityExistsException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public Question findById(final Long id) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final Question question = questionDao.findById(id);
            final List<Answer> answers = answerService.findByQuestionId(question.getId());
            question.setAnswers(answers);
            transactionManager.commit();
            return question;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Question> findByQuizId(final Long id) {

        transactionManager.begin();
        final List<Question> questions = questionDao.findByQuizId(id);

        for (Question question : questions) {
            final Long questionId = question.getId();
            final List<Answer> answers = answerService.findByQuestionId(questionId);
            question.setAnswers(answers);
        }

        transactionManager.commit();
        return questions;
    }

    @Override
    public void remove(final Question question) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            questionDao.removeById(question.getId());
            transactionManager.commit();
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<TestQuestion> startTest(final Long quizId) {
        final List<Question> questions = findByQuizId(quizId);
        final List<TestQuestion> testQuestions = new ArrayList<>();
        questions.forEach(question -> testQuestions.add(new TestQuestion(question, Collections.emptyList())));
        return testQuestions;
    }

    @Override
    public double endTest(final List<TestQuestion> test) {
        final int countCorrectAnswers = test.stream().mapToInt(testQuestion -> {
            final List<Answer> correctAnswers = testQuestion.getQuestion()
                    .getAnswers()
                    .stream()
                    .filter(answer -> answer.getType().equals(AnswerType.CORRECT))
                    .collect(toList());
            return testQuestion.getAnswers().containsAll(correctAnswers) &&
                    testQuestion.getAnswers().size() == correctAnswers.size() ? 1 : 0;
        }).sum();
        return (double) countCorrectAnswers / test.size() * 100;
    }
}
