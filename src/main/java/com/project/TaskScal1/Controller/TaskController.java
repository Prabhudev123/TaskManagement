package com.project.TaskScal1.Controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.TaskScal1.DTO.CreateTaskDTO;
import com.project.TaskScal1.DTO.ErrorResponseDTO;
import com.project.TaskScal1.DTO.UpdateTaskDTO;
import com.project.TaskScal1.Entity.TaskEntity;
import com.project.TaskScal1.Repository.NoteService;
import com.project.TaskScal1.Repository.TaskRepository;
import com.project.TaskScal1.DTO.TaskResponseDTO;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private TaskRepository taskRepository;
	private NoteService noteService;
	private ModelMapper modelMapper = new ModelMapper();
	
	public TaskController(TaskRepository taskRepository,NoteService noteService) {
		this.taskRepository = taskRepository;
		this.noteService = noteService;
	}
	
	@GetMapping("")
	public String printHello() {
		return "Hello Prabhu!";
	}
	@PostMapping("/")
	public ResponseEntity<TaskEntity> addDetails(@RequestBody CreateTaskDTO taskDTO) throws ParseException{
		TaskEntity task = taskRepository.addTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDeadline());
		return ResponseEntity.ok(task);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TaskEntity>> getTasks(){
		return ResponseEntity.ok(taskRepository.getTasks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
		TaskEntity task = taskRepository.getTaskById(id);
		var notes = noteService.getNotesForTask(id);
		System.out.println(id+" ");
		if(task == null) {
			System.out.println(task);
			return ResponseEntity.notFound().build();
		}
		var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
		taskResponse.setNotes(notes);
		return ResponseEntity.ok(taskResponse);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id,@RequestBody UpdateTaskDTO update) throws ParseException{
		TaskEntity task = taskRepository.updateTask(id, update.getDescription(), update.getDeadline(), update.getCompleted());
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}
	
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<ErrorResponseDTO> handleError(Exception ex){
		if(ex instanceof ParseException) {
			return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date!"));
		}
		ex.printStackTrace();
		return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Server Error!"));
	}
}
