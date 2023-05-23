package com.central.book.show.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	
	private String nameString;
	
	@ManyToMany
	@JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name="studentId"), inverseJoinColumns = @JoinColumn(name="teacherId"))
	private List<Teacher> teachers;
}
