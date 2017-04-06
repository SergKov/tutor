package com.getprepared.web.validation.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by koval on 02.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotEmptyConstraintValidatorTest {

    @InjectMocks
    private NotEmptyConstraintValidator validator;

    @Test
    public void requireIsValidWithEmptyString() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpacesString() {
        assertFalse(validator.isValid("           "));
    }

    @Test
    public void requireIsValidWithManySpacesAndSomeText() {
        assertTrue(validator.isValid(" 12 sdds 123     3 "));
    }

    @Test
    public void requireIsValidWithText() {
        assertTrue(validator.isValid("123"));
    }

    @Test(expected = NullPointerException.class)
    public void requireIsValidWithNull() {
        validator.isValid(null);
    }
}