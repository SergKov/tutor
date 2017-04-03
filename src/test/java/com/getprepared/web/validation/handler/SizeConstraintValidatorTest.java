package com.getprepared.web.validation.handler;

import com.getprepared.web.validation.ConstraintValidator;
import com.getprepared.web.validation.annotation.Size;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 03.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SizeConstraintValidatorTest {

    @Mock
    private Size size;

    @InjectMocks
    private SizeConstraintValidator validator;

    @Test
    public void requireIsValidWithZeroEdges() {
        when(size.min()).thenReturn(0);
        when(size.max()).thenReturn(0);
        assertTrue(validator.isValid(new String[]{ }));
    }

    @Test
    public void requireIsValidWithZeroEdgesAndNotEmptyArray() {
        when(size.min()).thenReturn(0);
        when(size.max()).thenReturn(0);
        assertFalse(validator.isValid(new String[]{" ", "12"}));
    }

    @Test
    public void requireIsValidWithIncorrectVerges() {
        when(size.min()).thenReturn(2);
        when(size.max()).thenReturn(1);
        assertFalse(validator.isValid(new String[]{ }));
    }

    @Test
    public void requireIsValidWithCorrectValue() {
        when(size.min()).thenReturn(1);
        when(size.max()).thenReturn(5);
        assertTrue(validator.isValid(new String[]{"", "", "234"}));
    }

    @Test
    public void requireIsValidWithIncorrectArrayLength() {
        when(size.min()).thenReturn(4);
        when(size.max()).thenReturn(8);
        assertFalse(validator.isValid(new String[]{"", "", "234"}));
    }
}