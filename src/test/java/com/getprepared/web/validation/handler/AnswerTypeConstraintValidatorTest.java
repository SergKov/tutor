package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.annotation.AnswerType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 01.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnswerTypeConstraintValidatorTest {

    @Mock
    private AnswerType answerType;

    @InjectMocks
    private AnswerTypeConstraintValidator validator;

    @Before
    public void setUp() {
        when(answerType.value()).thenReturn(Type.values());
    }

    @Test
    public void requireIsValidWithEmptyArray() throws Exception {
        assertFalse(validator.isValid(new String[]{"", ""}));
    }

    @Test
    public void requireIsValidWithFirstEmptyArgumentAndAnotherInvalid() throws Exception {
        assertFalse(validator.isValid(new String[]{"", "1"}));
    }

    @Test
    public void requireIsValidWithSecondEmptyArgumentAndAnotherInvalid() throws Exception {
        assertFalse(validator.isValid(new String[]{" 545", ""}));
    }

    @Test
    public void requireIsValidWithCorrectArgument() throws Exception {
        assertTrue(validator.isValid(new String[]{"CORRECT", "INCORRECT", "CORRECT", "CORRECT"}));
    }

    @Test
    public void requireIsValidWithCorrectArgumentsInRandomRegistr() throws Exception {
        assertTrue(validator.isValid(new String[]{"CoRrect", "INCORrECt", "cOrreCT", "correct"}));
    }

    @Test
    public void requireIsValidWithOneEmptyString() throws Exception {
        assertFalse(validator.isValid(new String[]{""}));
    }

    @Test
    public void requireIsValidWithOneCorrectArgument() throws Exception {
        assertFalse(validator.isValid(new String[]{"", "INCORRECT"}));
    }

}