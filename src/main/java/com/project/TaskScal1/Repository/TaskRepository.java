package com.project.TaskScal1.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.project.TaskScal1.Entity.TaskEntity;

@Service
public class TaskRepository {

	List<TaskEntity> tasks = new ArrayList<>();
	private int taskId = 1;
	private SimpleDateFormat deadlineFormat = new SimpleDateFormat("yyyy-mm-dd");
	
	public TaskEntity addTask(String title,String description,String deadline)throws ParseException {
		TaskEntity task = new TaskEntity();
		task.setId(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setDate(deadlineFormat.parse(deadline));//to do: validate date
		task.setCompleted(false);
		tasks.add(task);
		taskId++;
		
		return task;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}
	
	public TaskEntity getTaskById(int id) {
		//return tasks.stream().findAny().filter(task -> task.getId() == id).orElse(null);
		for(TaskEntity task:tasks) {
			if(task.getId() == id) {
				return task;
			}
		}
		return null;
	}
	
	public TaskEntity updateTask(int id,String description,String deadline,Boolean completed) throws ParseException {
		TaskEntity task = getTaskById(id);
		if(task == null) {
			return null;
		}
		
		if(description != null) {
			task.setDescription(description);
		}
		if(deadline!=null) {
			task.setDate(deadlineFormat.parse(deadline));
		}
		if(completed != null ) {
			task.setCompleted(completed);
		}
		return task;
	}
}
