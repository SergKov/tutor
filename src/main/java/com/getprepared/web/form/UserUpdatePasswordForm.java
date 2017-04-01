package com.getprepared.web.form;

import com.getprepared.web.validation.annotation.Pattern;

import static com.getprepared.web.constant.ValidationConstant.REGEX;

/**
 * Created by koval on 01.04.2017.
 */
public class UserUpdatePasswordForm {

    @Pattern(regexp = REGEX.PASSWORD)
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
