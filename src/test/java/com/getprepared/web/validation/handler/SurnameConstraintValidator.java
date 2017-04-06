package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.getprepared.web.constant.ValidationConstant.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 04.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SurnameConstraintValidator {

    @Mock
    private Pattern pattern;

    @InjectMocks
    private PatternConstraintValidator validator;

    @Before
    public void setUp() {
        when(pattern.regexp()).thenReturn(REGEX.SURNAME);
    }

    @Test
    public void requireIsValidWithEmptyString() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpaces() {
        assertFalse(validator.isValid("                           "));
    }

    @Test
    public void requireIsValidWithManySpacesAndLetters() {
        assertFalse(validator.isValid("         DFSDS   SD ds               "));
    }

    @Test
    public void requireIsValidWithEnglishSurname() {
        assertTrue(validator.isValid("Kovalchuk"));
    }

    @Test
    public void requireIsValidWithEnglishSurnameWithSpace() {
        assertFalse(validator.isValid("K ovalchuk"));
    }

    @Test
    public void requireIsValidWithRussianSurnameWithSpace() {
        assertFalse(validator.isValid("Довг аль"));
    }

    @Test
    public void requireIsValidWithManyRussianSurname() {
        assertTrue(validator.isValid("Довгаль"));
    }

    @Test
    public void requireIsValidWithSmallSurname() {
        assertTrue(validator.isValid("Ех"));
    }

    @Test
    public void requireIsValidWithBigWord() {
        assertFalse(validator.isValid("asdsfdfdsfsfdsfdsfdsfdsf"));
    }

    @Test(expected = NullPointerException.class)
    public void requireIsValidWithNull() {
        validator.isValid(null);
    }
}
