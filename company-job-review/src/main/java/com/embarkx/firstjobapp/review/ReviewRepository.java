package com.embarkx.firstjobapp.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Spring JPA will create an implementation of this method at runtime findBy CompanyId
    List<Review> findByCompanyId(Long companyId);
}
