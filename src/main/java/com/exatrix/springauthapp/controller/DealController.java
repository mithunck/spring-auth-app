package com.exatrix.springauthapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exatrix.springauthapp.model.Deal;
import com.exatrix.springauthapp.payload.PagedResponse;
import com.exatrix.springauthapp.repository.DealRepository;
import com.exatrix.springauthapp.repository.UserRepository;
import com.exatrix.springauthapp.security.CurrentUser;
import com.exatrix.springauthapp.security.UserPrincipal;
import com.exatrix.springauthapp.service.DealService;
import com.exatrix.springauthapp.util.AppConstants;

@RestController
@RequestMapping("/api/deals")
public class DealController {
    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealService dealService;

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @GetMapping
    public PagedResponse<Deal> getDeals(@CurrentUser UserPrincipal currentUser,
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return dealService.getAllDeals(currentUser, page, size);
    }


}
