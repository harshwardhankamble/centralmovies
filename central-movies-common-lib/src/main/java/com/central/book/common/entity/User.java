package com.central.book.common.entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotNull
	private String userName;
	
	@Email
	private String emailId;
	
	@NotNull
	private String password;
	
	private String mobileNumber;
	
	@OneToOne
	@JoinColumn(name = "roleId")
	private Role role;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
}
