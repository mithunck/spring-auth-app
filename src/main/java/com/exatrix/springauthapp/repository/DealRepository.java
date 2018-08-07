package com.exatrix.springauthapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exatrix.springauthapp.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Long>{
	Optional<Deal> findById(Long dealId);

    Page<Deal> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreatedBy(Long userId);

    List<Deal> findByIdIn(List<Long> dealIds);

    List<Deal> findByIdIn(List<Long> dealIds, Sort sort);
}
