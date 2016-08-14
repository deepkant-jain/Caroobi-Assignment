package com.deepkant.tmsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deepkant.tmsapp.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer>
{
	@Query("FROM Developer d where d.name = :argName")
    public Developer findByName(@Param(value = "argName") String argName);
	
	@Query("FROM Developer d where d.active = true")
	@Override
	public List<Developer> findAll();
}