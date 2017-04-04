package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.annotation.Service;
import com.getprepared.core.exception.EntityExistsException;
import com.getprepared.core.exception.EntityNotFoundException;
import com.getprepared.core.exception.QuizNotTerminatedException;
import com.getprepared.core.exception.QuizTerminatedException;
import com.getprepared.core.service.AnswerService;
import com.getprepared.core.service.QuestionService;
import com.getprepared.core.service.QuizService;
import com.getprepared.core.service.UserService;
import com.getprepared.persistence.dao.QuizDao;
import com.getprepared.persistence.database.pagination.PageableData;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Quiz;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Created by koval on 15.01.2017.
 */
@Service("quizService")
public class QuizServiceImpl extends AbstractService implements QuizService {

    @Inject
    private QuizDao quizDao;

    @Inject
    private UserService userService;

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

            initQuiz(quiz);

            transactionManager.commit();
            return quiz;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Quiz> findAllByTutorId(final Long id, final PageableData page) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final List<Quiz> quizzes = quizDao.findAllByTutorId(id, page);
            page.setNumberOfElements(quizDao.countFoundRows());

            initQuiz(quizzes);

            transactionManager.commit();
            return quizzes;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public List<Quiz> findAllActive(final PageableData page) throws EntityNotFoundException {
        try {
            transactionManager.begin();
            final List<Quiz> createdQuizzes = quizDao.findAllCreated(page);
            page.setNumberOfElements(quizDao.countFoundRows());

            initQuiz(createdQuizzes);

            transactionManager.commit();
            return createdQuizzes;
        } catch (final EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    private void initQuiz(final List<Quiz> quizzes) throws EntityNotFoundException {
        for (final Quiz quiz : quizzes) {
            final List<Question> questions = questionService.findByQuizId(quiz.getId());

            for (final Question question : questions) {
                final List<Answer> answers = answerService.findByQuestionId(question.getId());
                question.setAnswers(answers);
            }
        }
    }

    @Override
    public void active(final Long id) throws QuizTerminatedException, EntityNotFoundException,
            QuizNotTerminatedException {
        try {
            transactionManager.begin();
            final Quiz quiz = quizDao.findById(id);
            initQuiz(quiz);
            checkActive(quiz);
            checkQuizQuestions(quiz);
            quizDao.activeQuiz(quiz.getId());
            transactionManager.commit();
        } catch (EntityNotFoundException | QuizTerminatedException | QuizNotTerminatedException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    private void initQuiz(final Quiz quiz) throws EntityNotFoundException {

        final List<Question> questions = questionService.findByQuizId(quiz.getId());

        for (final Question question : questions) {
            final List<Answer> answers = answerService.findByQuestionId(question.getId());
            question.setAnswers(answers);
        }
        quiz.setQuestions(questions);
    }


    private void checkQuizQuestions(final Quiz quiz) throws QuizNotTerminatedException { // TODO rename
        final List<Question> questions = quiz.getQuestions();
        if (isEmpty(questions)) {
            throw new QuizNotTerminatedException(String.format("Quiz %s is empty.", quiz.getName()));
        }
    }

    @Override
    public void update(final Quiz quiz) throws QuizTerminatedException, EntityExistsException, EntityNotFoundException {
        try { // TODO ??
            transactionManager.begin();
            final Quiz oldQuiz = quizDao.findById(quiz.getId());
            checkActive(oldQuiz);
            quizDao.update(quiz.getName(), quiz.getId());
            transactionManager.commit();
        } catch (EntityExistsException | QuizTerminatedException | EntityNotFoundException e) {
            transactionManager.rollback();
            throw e;
        }
    }

    @Override
    public void remove(final Long id) {
        transactionManager.begin();
        quizDao.remove(id);
        transactionManager.commit();
    }
}
