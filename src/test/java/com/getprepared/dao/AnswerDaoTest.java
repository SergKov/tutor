package com.getprepared.dao;

import com.getprepared.dao.impl.DaoFactory;
import com.getprepared.domain.Answer;
import com.getprepared.domain.AnswerType;
import com.getprepared.domain.Question;
import com.getprepared.exception.EntityExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static com.getprepared.constant.ServerConstants.DAOS.ANSWER_DAO;
import static org.mockito.Mockito.when;

/**
 * Created by koval on 03.01.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerDaoTest {

    private AnswerDao dao = getAnswerDao();

    @Spy
    private Answer answer;

    @Mock
    private Question question;

    @Test
    public void requireSave() throws EntityExistsException {
        when(answer.getQuestion()).thenReturn(question);
        when(question.getId()).thenReturn(1L);
        when(answer.getType()).thenReturn(AnswerType.CORRECT);
        dao.save(answer);
    }

    public AnswerDao getAnswerDao() {
        return DaoFactory.getInstance().getDao(ANSWER_DAO, AnswerDao.class);
    }
}
