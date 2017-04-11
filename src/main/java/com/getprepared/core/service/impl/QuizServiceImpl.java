package com.getprepared.core.service.impl;

import com.getprepared.annotation.Inject;
import com.getprepared.core.annotation.Service;
import com.getprepared.core.annotation.Transactional;
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
import com.getprepared.persistence.domain.User;
import org.apache.log4j.Logger;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Created by koval on 15.01.2017.
 */
@Service("quizService")
public class QuizServiceImpl extends AbstractService implements QuizService {

    private static final Logger LOG = Logger.getLogger(QuizServiceImpl.class);

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
        quizDao.save(quiz);
    }

    @Override
    @Transactional
    public Quiz findById(final Long id) throws EntityNotFoundException {
        final Quiz quiz = quizDao.findById(id);
        initQuiz(quiz);
        return quiz;
    }

    @Override
    @Transactional
    public List<Quiz> findAllByTutorId(final Long id, final PageableData page) {
        page.setNumberOfElements(quizDao.countFoundRows());
        checkPage(page);
        final List<Quiz> quizzes = quizDao.findAllByTutorId(id, page);

        quizzes.forEach(this::initQuiz);
        return quizzes;
    }

    @Override
    @Transactional
    public List<Quiz> findAllActive(final PageableData page) {
        page.setNumberOfElements(quizDao.countFoundRows());
        checkPage(page);
        final List<Quiz> createdQuizzes = quizDao.findAllCreated(page);

        createdQuizzes.forEach(this::initQuiz);
        return createdQuizzes;
    }

    @Override
    @Transactional
    public void active(final Long id) throws QuizTerminatedException, EntityNotFoundException, QuizNotTerminatedException {
        final Quiz quiz = quizDao.findById(id);

        initQuiz(quiz);
        checkActive(quiz);
        checkNotEmpty(quiz);

        quizDao.activeQuiz(quiz.getId());
    }

    @Override
    @Transactional
    public void update(final Quiz quiz) throws QuizTerminatedException, EntityExistsException, EntityNotFoundException {
        final Quiz oldQuiz = quizDao.findById(quiz.getId());
        checkActive(oldQuiz);
        quizDao.update(quiz.getName(), quiz.getId());
    }

    @Override
    public void remove(final Long id) {
        quizDao.remove(id);
    }

    private void checkNotEmpty(final Quiz quiz) throws QuizNotTerminatedException {
        final List<Question> questions = quiz.getQuestions();
        if (isEmpty(questions)) {
            throw new QuizNotTerminatedException(String.format("Quiz %s is empty.", quiz.getName()));
        }
    }

    private void initQuiz(final Quiz quiz) {
        final List<Question> questions = questionService.findByQuizId(quiz.getId());

        for (final Question question : questions) {
            final List<Answer> answers = answerService.findByQuestionId(question.getId());
            question.setAnswers(answers);
        }
        quiz.setQuestions(questions);

        try {
            final User user = userService.findById(quiz.getUser().getId());
            quiz.setUser(user);
        } catch (final EntityNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
