package com.getprepared.web.form;

import com.getprepared.web.validation.annotation.Pattern;

import static com.getprepared.web.constant.ValidationConstant.REGEX;

/**
 * Created by koval on 01.04.2017.
 */
public class QuizUpdateForm {

    @Pattern(regexp = REGEX.QUIZ_NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
