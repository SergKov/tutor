package com.getprepared.web.validation.handler;

import com.getprepared.persistence.domain.Role;
import com.getprepared.web.validation.annotation.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private UserRoleConstraintValidator userRoleConstraintValidator;

    @Test
    public void requireIsValidWithIncorrectRole() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertFalse(userRoleConstraintValidator.isValid("stdent"));
    }

    @Test
    public void requireIsValidWithEmptyString() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertFalse(userRoleConstraintValidator.isValid(""));
    }

    @Test
    public void requireIsValidWithStudentInOtherRegister() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertTrue(userRoleConstraintValidator.isValid("StuDENt"));
    }

    @Test
    public void requireIsValidWithTutorInOtherRegister() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertTrue(userRoleConstraintValidator.isValid("TuTOr"));
    }

    @Test
    public void requireIsValidWithRoleStudent() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertTrue(userRoleConstraintValidator.isValid("Student"));
    }

    @Test
    public void requireIsValidWithRoleTutor() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertTrue(userRoleConstraintValidator.isValid("Tutor"));
    }

    @Test
    public void requireIsValidWithNotExistsRole() throws Exception {
        when(userRole.value()).thenReturn(Role.values());
        assertFalse(userRoleConstraintValidator.isValid("Manager"));
    }
}