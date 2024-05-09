package com.project.TaskScal1.DTO;

import com.project.TaskScal1.Entity.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NoteResponseDTO {
	private Integer taskId;
	private NoteEntity note;
	public NoteResponseDTO(Integer taskId,NoteEntity note) {
		this.taskId = taskId;
		this.note = note;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public NoteEntity getNote() {
		return note;
	}
	public void setNote(NoteEntity note) {
		this.note = note;
	}
}
