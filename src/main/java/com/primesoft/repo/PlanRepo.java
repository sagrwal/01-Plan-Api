package com.primesoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primesoft.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan,Integer> {

}
