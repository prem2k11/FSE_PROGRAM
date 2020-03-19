package com.fse.microservice.task.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.microservice.task.service.entity.TaskEntity;

public interface TaskServiceRepository extends JpaRepository<TaskEntity, Integer>{

	TaskEntity findBytaskid(int taskid);

}
