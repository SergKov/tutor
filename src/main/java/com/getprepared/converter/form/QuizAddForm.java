package com.getprepared.converter.form;

import com.getprepared.annotation.Pattern;

/**
 * Created by koval on 08.03.2017.
 */
public class QuizAddForm {

    @Pattern(regexp = "^[\\S]{2,20}$")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
