package com.getprepared.web.form;

import com.getprepared.web.validation.annotation.Pattern;

import static com.getprepared.web.constant.ValidationConstant.REGEX;

/**
 * Created by koval on 08.03.2017.
 */
public class QuizAddForm {

    @Pattern(regexp = REGEX.QUIZ_NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
