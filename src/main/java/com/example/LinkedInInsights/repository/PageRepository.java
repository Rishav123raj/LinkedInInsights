package com.example.LinkedInInsights.repository;

import com.example.linkedininsights.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByFollowersBetween(int min, int max);
    List<Page> findByIndustryContaining(String industry);
    List<Page> findByNameContaining(String name);
}
