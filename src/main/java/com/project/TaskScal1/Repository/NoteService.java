package com.project.TaskScal1.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.TaskScal1.Entity.NoteEntity;
import com.project.TaskScal1.Entity.TaskEntity;

@Service
public class NoteService {
	private TaskRepository taskService;
	private HashMap<Integer,TaskNoteHolder> taskNoteHolder = new HashMap<>();
	public NoteService(TaskRepository taskService) {
		this.taskService = taskService;
	}

	public class TaskNoteHolder{
		protected int noteId = 1;
		protected ArrayList<NoteEntity> notes  = new ArrayList<>();
	}
	
	public List<NoteEntity> getNotesForTask(int taskId){
		TaskEntity task = taskService.getTaskById(taskId);
		if(task == null)
			return null;
		if(taskNoteHolder.get(taskId)==null) {
			taskNoteHolder.put(taskId, new TaskNoteHolder());
		}
		return taskNoteHolder.get(taskId).notes;
	}
	
	public NoteEntity addNoteForTask(int id,String title,String body) {
		TaskEntity task = taskService.getTaskById(id);
		if(task == null)
			return null;
		if(taskNoteHolder.get(id)==null) {
			taskNoteHolder.put(id, new TaskNoteHolder());
		}
		TaskNoteHolder taskNote = taskNoteHolder.get(id);
		NoteEntity note = new NoteEntity();
		note.setId(taskNote.noteId);
		note.setTitle(title);
		note.setBody(body);
		taskNote.notes.add(note);
		taskNote.noteId++;
		return note;
		
	}
}
