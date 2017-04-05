package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.annotation.Transactional;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.persistence.dao.QuestionDao;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.persistence.domain.Type;
import com.getprepared.web.dto.TestQuestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by koval on 14.01.2017.
 */
@Service("questionService")
public class QuestionServiceImpl extends AbstractService implements QuestionService {

    @Inject
    private QuestionDao questionDao;

    @Inject
    private AnswerService answerService;

    @Inject
    private QuizService quizService;

    @Override
    @Transactional
    public void save(final Question question) throws EntityExistsException {
        questionDao.save(question);

        final List<Answer> answers = question.getAnswers();
        answerService.save(answers);
    }

    @Override
    @Transactional
    public Question findById(final Long id) throws EntityNotFoundException {
        final Question question = questionDao.findById(id);
        final List<Answer> answers = answerService.findByQuestionId(question.getId());
        question.setAnswers(answers);
        return question;
    }

    @Override
    @Transactional
    public List<Question> findByQuizId(final Long id) {
        final List<Question> questions = questionDao.findByQuizId(id);

        for (final Question question : questions) {
            final Long questionId = question.getId();
            final List<Answer> answers = answerService.findByQuestionId(questionId);
            question.setAnswers(answers);
        }

        return questions;
    }

    @Override
    @Transactional
    public List<Question> findByQuizIdRandom(final Long id) {
        final List<Question> questions = questionDao.findByQuizIdRandom(id);

        for (final Question question : questions) {
            final Long questionId = question.getId();
            final List<Answer> answers = answerService.findByQuestionIdRandom(questionId);
            question.setAnswers(answers);
        }

        return questions;
    }

    @Override
    @Transactional
    public void remove(final Long id) throws EntityNotFoundException, QuizTerminatedException {
        final Question question = findById(id);
        final Long quizId = question.getQuiz().getId();
        final Quiz quiz = quizService.findById(quizId);
        checkActive(quiz);
        questionDao.remove(question.getId());
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
                    .filter(answer -> answer.getType().equals(Type.CORRECT))
                    .collect(toList());
            return testQuestion.getAnswers().size() == correctAnswers.size() &&
                    testQuestion.getAnswers().containsAll(correctAnswers) ? 1 : 0;
        }).sum();
        return (double) countCorrectAnswers / test.size() * 100;
    }
}
