package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Role;
import com.getprepared.web.validation.annotation.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by koval on 02.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRoleConstraintValidatorTest {

    @Mock
    private UserRole userRole;

    @InjectMocks
    private UserRoleConstraintValidator validator;

    @Before
    public void setUp() {
        when(userRole.value()).thenReturn(Role.values());
    }

    @Test
    public void requireIsValidWithIncorrectRole() throws Exception {
        assertFalse(validator.isValid("stdent"));
    }

    @Test
    public void requireIsValidWithEmptyString() throws Exception {
        assertFalse(validator.isValid(""));
    }

    @Test
    public void requireIsValidWithStudentInOtherRegister() throws Exception {
        assertTrue(validator.isValid("StuDENt"));
    }

    @Test
    public void requireIsValidWithTutorInOtherRegister() throws Exception {
        assertTrue(validator.isValid("TuTOr"));
    }

    @Test
    public void requireIsValidWithRoleStudent() throws Exception {
        assertTrue(validator.isValid("Student"));
    }

    @Test
    public void requireIsValidWithRoleTutor() throws Exception {
        assertTrue(validator.isValid("Tutor"));
    }

    @Test
    public void requireIsValidWithNotExistsRole() throws Exception {
        assertFalse(validator.isValid("Manager"));
    }
}