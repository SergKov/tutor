package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.Pattern;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.getprepared.web.constant.ValidationConstant.REGEX;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by koval on 04.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailConstraintValidatorTest {

    @Mock
    private Pattern pattern;

    @InjectMocks
    private PatternConstraintValidator validator;

    @Before
    public void setUp() {
        when(pattern.regexp()).thenReturn(REGEX.EMAIL);
    }

    @Test
    public void requireIsValidWithEmptyEmail() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpacesAndSign() {
        assertFalse(validator.isValid("         @   "));
    }

    @Test
    public void requireIsValidWithCorrectEmail() {
        assertTrue(validator.isValid("test@ukr.net"));
    }

    @Test
    public void requireIsValidWithoutDot() {
        assertFalse(validator.isValid("test@ukrnet"));
    }

    @Test
    public void requireIsValidWithManyLettersAfterDot() {
        assertFalse(validator.isValid("test@ukr.net...aervd"));
    }

    @Test
    public void requireIsValidWithoutDotAndSign() {
        assertFalse(validator.isValid("test_testnet"));
    }

    @Test
    public void requireIsValidWithEmailWithoutDogSign() {
        assertFalse(validator.isValid("testtest.net"));
    }

    @Test
    public void requireIsValidWithLongWorld() {
        assertTrue(validator.isValid("testtestnetestingtestingtesting_Test_texd@testing_test.com"));
    }

    @Test(expected = NullPointerException.class)
    public void requireIsValidWithNull() {
        validator.isValid(null);
    }
}