package com.person.googlenotesclone.todo;

import javax.persistence.*;

@Entity
@Table
public class Todo {
	@Id
	@SequenceGenerator(
			name = "todo_generator",
			sequenceName = "todo_generator",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "todo_generator"
			)
	private long id;
	private String title;
	private String description;
	
	public Todo(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	public Todo() {
		
	}

	public Todo(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
	
	
	
	
	
}
