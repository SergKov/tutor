package com.getprepared.controller.tutor.speciality_page;

import com.getprepared.controller.common.abstract_classes.AbstractSpecialityController;
import com.getprepared.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.getprepared.constant.PageConstants.PAGES;
import static com.getprepared.constant.ServerConstants.SERVICES.SPECIALITY_SERVICE;

/**
 * Created by koval on 21.01.2017.
 */
public class TutorSpecialityController extends AbstractSpecialityController {

    private SpecialityService specialityService;

    @Override
    public void init() {
        specialityService = getServiceFactory().getService(SPECIALITY_SERVICE, SpecialityService.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fillPage(request, specialityService);
        return PAGES.TUTOR_SPECIALITIES;
    }
}
