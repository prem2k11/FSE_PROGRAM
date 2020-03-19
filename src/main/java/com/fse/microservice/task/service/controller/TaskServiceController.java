package com.fse.microservice.task.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fse.microservice.task.service.entity.ParentTaskEntity;
import com.fse.microservice.task.service.entity.TaskEntity;
import com.fse.microservice.task.service.repository.ParentTaskRepository;
import com.fse.microservice.task.service.repository.TaskServiceRepository;

@RestController
@RequestMapping(value="/task")
public class TaskServiceController {

	@Autowired
	private TaskServiceRepository taskServiceRepository;
	
	@Autowired
	private ParentTaskRepository parentTaskServiceRepository;
	
	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public ResponseEntity<String> addTask(@RequestBody TaskEntity task)
	{
		taskServiceRepository.save(task);
		
		return new ResponseEntity<String>("Task added successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTask", method = RequestMethod.GET)
	public ResponseEntity<TaskEntity> getTask(@RequestParam("id") String id)
	{
		TaskEntity taskEntity = new TaskEntity();
		if(id != null && !id.isEmpty())
		{
			int taskid = Integer.parseInt(id);
			taskEntity = (TaskEntity) taskServiceRepository.findBytaskid(taskid);
			return new ResponseEntity<TaskEntity>(taskEntity,HttpStatus.OK);
			
		}
		
		return new ResponseEntity<TaskEntity>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/getAllTasks", method = RequestMethod.GET)
	public ResponseEntity<List<TaskEntity>> getAllTask()
	{
		List<TaskEntity> taskEntity = new ArrayList<TaskEntity>();
		taskEntity = (List<TaskEntity>) taskServiceRepository.findAll();
		return new ResponseEntity<List<TaskEntity>>(taskEntity,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getAllParent", method = RequestMethod.GET)
	public  ResponseEntity<List<ParentTaskEntity>> getAllParent() {
		List<ParentTaskEntity> parentList = new ArrayList<ParentTaskEntity>();
		parentList = (List<ParentTaskEntity>) parentTaskServiceRepository.findAll();
		return new ResponseEntity<List<ParentTaskEntity>>(parentList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getParentTask", method = RequestMethod.GET)
	public  ResponseEntity<ParentTaskEntity> getParentTask(@RequestParam("name") String name) {
		ParentTaskEntity parentTask = new ParentTaskEntity();
		parentTask = (ParentTaskEntity) parentTaskServiceRepository.findByparenttask(name);
		return new ResponseEntity<ParentTaskEntity>(parentTask, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addParent", method = RequestMethod.POST)
	public ResponseEntity<ParentTaskEntity> addParent(@RequestBody TaskEntity task) {
		ParentTaskEntity entity = new ParentTaskEntity();
		entity.setParenttask(task.getTaskname());
		return new ResponseEntity<ParentTaskEntity>(parentTaskServiceRepository.save(entity), HttpStatus.OK);
	}
	
	//@PutMapping(value="/endTask/{taskId}",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/endTask", method = RequestMethod.PUT)
	public ResponseEntity<TaskEntity> endTask(@RequestParam("id") String id){
			
		TaskEntity taskEntity = new TaskEntity();
		if(id != null && !id.isEmpty())
		{
			int taskid = Integer.parseInt(id);
			taskEntity = (TaskEntity) taskServiceRepository.findBytaskid(taskid);
			taskEntity.setStatus("11");
			taskEntity.setEnddate(LocalDate.now());
		}
		return new ResponseEntity<TaskEntity>(taskServiceRepository.save(taskEntity), HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	public ResponseEntity<String> updateTask(@RequestBody TaskEntity entity)
	{	

		if(entity != null)
		{
			taskServiceRepository.save(entity);
			return new ResponseEntity<String>("Task Updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Task Found",HttpStatus.BAD_REQUEST);
	}
	
	 
	
}
