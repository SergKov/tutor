package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.Id;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by koval on 14.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class IdConstraintValidatorTest {

    @Mock
    private Id id;

    @InjectMocks
    private ConstraintValidator validator = new IdConstraintValidator();

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithEmptyString() {
        assertFalse(validator.isValid(""));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithManySpaces() {
        assertFalse(validator.isValid(""));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringNuber() {
        assertTrue(validator.isValid("120"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringLongMaxValue() {
        assertTrue(validator.isValid("9223372036854775807"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringNegativeValue() {
        assertFalse(validator.isValid("-4568"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringDoubleValue() {
        assertFalse(validator.isValid("1.978"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringCharValue() {
        assertFalse(validator.isValid("abc"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringCharNumberValue() {
        assertFalse(validator.isValid("2a23b_123c1"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringNumberWithDashValue() {
        assertFalse(validator.isValid("1_000"));
    }

    @Test
    @SuppressWarnings("all")
    public void requireIsValidWithStringBiggerThanLongNumber() {
        assertFalse(validator.isValid("9223372036854775808"));
    }

}