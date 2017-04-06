package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 04.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PasswordConstraintValidator {

    @Mock
    private Pattern pattern;

    @InjectMocks
    private PatternConstraintValidator validator;

    @Before
    public void setUp() {
        when(pattern.regexp()).thenReturn(REGEX.PASSWORD);
    }

    @Test
    public void requireIsValidWithEmptyString() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpaces() {
        assertFalse(validator.isValid("                                     "));
    }

    @Test
    public void requireIsValidWithPasswordWithoutBigLettersAndNumber() {
        assertFalse(validator.isValid("testtesttest234"));
    }

    @Test
    public void requireIsValidWithPasswordWithoutBigLetters() {
        assertFalse(validator.isValid("testtesttest"));
    }

    @Test
    public void requireIsValidWithPasswordWithoutSmallLettersAndNumber() {
        assertFalse(validator.isValid("GYGDSHFGSJGDSSDHJK"));
    }

    @Test
    public void requireIsValidWithPasswordWithoutSmallLetters() {
        assertFalse(validator.isValid("GYGDSHFGSJGDS12SDHJK"));
    }

    @Test
    public void requireIsValidWithPasswordWithOneBigLetter() {
        assertTrue(validator.isValid("Testing234"));
    }

    @Test
    public void requireIsValidWithPasswordWithoutNumbers() {
        assertFalse(validator.isValid("Testing"));
    }

    @Test
    public void requireIsValidWithPasswordWithCorrectPassword() {
        assertTrue(validator.isValid("sdsdETesting11"));
    }

    @Test
    public void requireIsValidWithBigWord() {
        assertFalse(validator.isValid("TestingTestingTestingTEsbhjgjhjhkdfsj67sdadsd"));
    }

    @Test
    public void requireIsValidWithOneLetter() {
        assertFalse(validator.isValid("w"));
    }

    @Test
    public void requireIsValidWithOneNumber() {
        assertFalse(validator.isValid("1"));
    }

    @Test(expected = NullPointerException.class)
    public void requireIsValidWithNull() {
        validator.isValid(null);
    }
}
