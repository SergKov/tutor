package com.getprepared.web.form;

import com.getprepared.web.validation.annotation.NotEmpty;

/**
 * Created by koval on 01.04.2017.
 */
public class QuizUpdateForm {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
