package com.fse.microservice.task.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.microservice.task.service.entity.ParentTaskEntity;


public interface ParentTaskRepository extends JpaRepository<ParentTaskEntity, Integer>{

	ParentTaskEntity findByparenttask(String parenttask);
	
}
