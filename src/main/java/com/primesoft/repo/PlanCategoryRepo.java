package com.primesoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primesoft.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory,Integer> {

}
