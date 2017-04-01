package com.getprepared.web.validation.handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by koval on 02.04.2017.
 */
@RunWith(Parameterized.class)
public class NotEmptyConstraintValidatorTest {

    private NotEmptyConstraintValidator validator;

    @Before
    public void initialize() {
        validator = new NotEmptyConstraintValidator();
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {"2", false},
                {"", true},
                {".", false},
                {"  ", true},
                {"           ", true},
                {"      2     ", false},
                {"null", false}
        });
    }

    @Test
    public void test() {

    }
}