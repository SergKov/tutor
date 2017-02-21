package com.getprepared.service.impl;

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

import static com.getprepared.constant.ServerConstants.DAOS.QUIZ_DAO;
import static com.getprepared.constant.ServerConstants.SERVICES.ANSWER_SERVICE;
import static com.getprepared.constant.ServerConstants.SERVICES.QUESTION_SERVICE;

/**
 * Created by koval on 15.01.2017.
 */
public class QuizServiceImpl extends AbstractService implements QuizService {

    public QuizServiceImpl() { }

    @Override
    public void save(final Quiz quiz) throws EntityExistsException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            quizDao.save(quiz);
            getTransactionManager().commit();
        } catch (final EntityExistsException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public Quiz findById(final Long id) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            final Quiz quiz = quizDao.findById(id);
            final QuestionService questionService = getQuestionService();
            final List<Question> questions = questionService.findByQuizId(id);
            quiz.setQuestions(questions);
            getTransactionManager().commit();
            return quiz;
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    @Override
    public List<Quiz> findAll()  {
        getTransactionManager().begin();
        final QuizDao quizDao = getDao();
        final List<Quiz> quizzes = quizDao.findAll();

        quizzes.forEach(quiz -> {
            final QuestionService questionService = getQuestionService();
            final List<Question> questions = questionService.findByQuizId(quiz.getId());

            questions.forEach(question -> {
                final AnswerService answerService = getAnswerService();
                final List<Answer> answers = answerService.findByQuestionId(question.getId());
                question.setAnswers(answers);
            });

            quiz.setQuestions(questions);
        });
        getTransactionManager().commit();
        return quizzes;
    }

    @Override
    public List<Quiz> findAllCreated() {
        final List<Quiz> allQuizzes = findAll();
        final List<Quiz> createdQuizzes = new ArrayList<>();

        allQuizzes.forEach(quiz -> {
            if (!CollectionUtils.isEmpty(quiz.getQuestions()) && quiz.getQuestions().size() >= 2) {
                createdQuizzes.add(quiz);
            }
        });
        return createdQuizzes;
    }

    @Override
    public void remove(final Quiz quiz) throws EntityNotFoundException {
        try {
            getTransactionManager().begin();
            final QuizDao quizDao = getDao();
            quizDao.remove(quiz.getId());
            getTransactionManager().commit();
        } catch (final EntityNotFoundException e) {
            getTransactionManager().rollback();
            throw e;
        }
    }

    private QuizDao getDao() {
        return getDaoFactory().getDao(QUIZ_DAO, QuizDao.class);
    }

    private QuestionService getQuestionService() {
        return ServiceFactory.getInstance().getService(QUESTION_SERVICE, QuestionService.class);
    }

    private AnswerService getAnswerService() {
        return ServiceFactory.getInstance().getService(ANSWER_SERVICE, AnswerService.class);
    }
}
