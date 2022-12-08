package com.primesoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.primesoft.entity.Plan;
import com.primesoft.entity.PlanCategory;
import com.primesoft.repo.PlanCategoryRepo;
import com.primesoft.repo.PlanRepo;

public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		
		List<PlanCategory> categories =planCategoryRepo.findAll();
		
		Map<Integer, String> categoryMap =new HashMap<>();
		
		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved=planRepo.save(plan);
		
	/*	if(saved.getPlanId()!=null) {
			return true;
		}else {
		return false;
	}*/
		
		//return saved.getPlanId()!=null ? true : false;
		
		return saved.getPlanId() !=null;
	}

	@Override
	public List<Plan> getAllPlan() {
	
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);//ctrl +1 +Enter
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan save= planRepo.save(plan);//upsert method i.e insert also update
		
		return plan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		boolean status =false;
		try{
			planRepo.deleteById(planId);
			status =true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		
	Optional<Plan> findById = planRepo.findById(planId);
	if(findById.isPresent()) {
		Plan plan =findById.get();
		plan.setActiveSw(status);
		planRepo.save(plan);
		return true;	
	}
		return false;
	}

}
