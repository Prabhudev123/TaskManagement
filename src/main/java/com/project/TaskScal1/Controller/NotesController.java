package com.project.TaskScal1.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.TaskScal1.Repository.NoteService;
import com.project.TaskScal1.DTO.CreateNoteDTO;
import com.project.TaskScal1.DTO.NoteResponseDTO;
import com.project.TaskScal1.Entity.NoteEntity;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
	
	NoteService noteService;
	public NotesController(NoteService noteService) {
		this.noteService = noteService;
	}

	@GetMapping("")
	public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
		var task = noteService.getNotesForTask(taskId);
		return ResponseEntity.ok(task);
	}
	
	@PostMapping("")
	public ResponseEntity<NoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId,@RequestBody CreateNoteDTO createNoteDTO){
		var note = noteService.addNoteForTask(taskId, createNoteDTO.getTitle(), createNoteDTO.getBody());
		return ResponseEntity.ok(new NoteResponseDTO(taskId,note));
	}
}
