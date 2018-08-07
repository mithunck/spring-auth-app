package com.exatrix.springauthapp.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exatrix.springauthapp.exception.BadRequestException;
import com.exatrix.springauthapp.model.Deal;
import com.exatrix.springauthapp.payload.PagedResponse;
import com.exatrix.springauthapp.repository.DealRepository;
import com.exatrix.springauthapp.repository.UserRepository;
import com.exatrix.springauthapp.security.UserPrincipal;
import com.exatrix.springauthapp.util.AppConstants;

@Service
public class DealService {
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private DealRepository dealRepository;
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    public PagedResponse<Deal> getAllDeals(UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);
        // Retrieve deals
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Deal> deals = dealRepository.findAll(pageable);

        if(deals.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), deals.getNumber(),
                    deals.getSize(), deals.getTotalElements(), deals.getTotalPages(), deals.isLast());
        }
        
        List<Deal> dealResponse = deals.getContent();

        return new PagedResponse<>(dealResponse, deals.getNumber(),
                deals.getSize(), deals.getTotalElements(), deals.getTotalPages(), deals.isLast());
        

    }
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }


    
}
