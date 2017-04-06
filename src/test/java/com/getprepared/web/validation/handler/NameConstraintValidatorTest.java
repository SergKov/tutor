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
public class NameConstraintValidatorTest {

    @Mock
    private Pattern pattern;

    @InjectMocks
    private PatternConstraintValidator validator;

    @Before
    public void setUp() {
        when(pattern.regexp()).thenReturn(REGEX.NAME);
    }

    @Test
    public void requireIsValidWithEmptyString() {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithManySpaces() {
        assertFalse(validator.isValid("                "));
    }

    @Test
    public void requireIsValidWithNumbers() {
        assertFalse(validator.isValid("asds1adfs"));
    }

    @Test
    public void requireIsValidWithRussianName() {
        assertTrue(validator.isValid("Дмитрий"));
    }

    @Test
    public void requireIsValidWithNameRussianWithSpaces() {
        assertFalse(validator.isValid("Дм итрий"));
    }

    @Test
    public void requireIsValidWithEnglishName() {
        assertTrue(validator.isValid("Pol"));
    }

    @Test
    public void requireIsValidWithEnglishNameWithSpaces() {
        assertFalse(validator.isValid("Po l"));
    }

    @Test
    public void requireIsValidWithSmallName() {
        assertTrue(validator.isValid("Ян"));
    }

    @Test
    public void requireIsValidWithLongIncorrectName() {
        assertFalse(validator.isValid("Asdsadsasfdsfdgfdsgfgddgfdfg"));
    }

    @Test(expected = NullPointerException.class)
    public void requireIsValidWithNull() {
        validator.isValid(null);
    }
}
