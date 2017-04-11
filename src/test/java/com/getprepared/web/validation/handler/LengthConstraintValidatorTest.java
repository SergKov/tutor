package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.Length;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by koval on 11.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LengthConstraintValidatorTest {

    @Mock
    private Length length;

    @InjectMocks
    private LengthConstraintValidator validator;

    @Before
    public void setUp() {
        when(length.max()).thenReturn(10);
    }

    @Test
    public void requireIsValidWithEmptyString() {
        assertTrue(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpacesString() {
        assertFalse(validator.isValid("                                             "));
    }

    @Test
    public void requireIsValidWithCorrectText() {
        assertTrue(validator.isValid(" 12 sdds "));
    }

    @Test
    public void requireIsValidWithInCorrectText() {
        assertFalse(validator.isValid("21376532463576t3rygwhfdbsfjhdbfj3264873463284"));
    }
}