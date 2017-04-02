package com.getprepared.web.form;

import com.getprepared.web.validation.annotation.Pattern;

import static com.getprepared.web.constant.ValidationConstant.REGEX;

/**
 * Created by koval on 02.04.2017.
 */
public class PageableForm {

    @Pattern(regexp = REGEX.NUMBER)
    private String numberOfElements;

    @Pattern(regexp = REGEX.NUMBER)
    private String currentPage;

    public String getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(String numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
