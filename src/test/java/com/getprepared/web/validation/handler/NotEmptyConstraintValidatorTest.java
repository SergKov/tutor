package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.annotation.NotEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by koval on 02.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotEmptyConstraintValidatorTest {

    @InjectMocks
    private NotEmptyConstraintValidator validator;

    @Test
    public void requireIsValidWithEmptyString() {
        Assert.assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpacesString() {
        Assert.assertFalse(validator.isValid("           "));
    }

    @Test
    public void requireIsValidWithManySpacesAndSomeText() {
        Assert.assertTrue(validator.isValid(" 12 sdds 123     3 "));
    }

    @Test
    public void requireIsValidWithText() {
        Assert.assertTrue(validator.isValid("123"));
    }
}