package com.getprepared.converter.form;

import com.getprepared.annotation.Pattern;

import static com.getprepared.constant.UtilsConstant.REGEX;

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
