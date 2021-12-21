package com.poc.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
@Entity
@Table(name="USER_ACCOUNT")
public class UserAccount 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer userId;
	
	@NotNull
	@Size(min = 3, message = "name should have atleast 3 characters")
	@Column(name="FIRST_NAME")
	private String fname;
	
	@NotNull
	@Size(min = 3, message = "name should have atleast 3 characters")
	@Column(name="LAST_NAME")
	private String lname;
	
	@NotNull(message = "Mobile Number is required.")
	@Column(name="USER_MOBILE")
	private Long phno;
	
	
	@NotNull(message = "User Pincode is required.")
	@Column(name="USER_PINCODE")
	private Integer userPincode;
	
	@NotNull(message = "The date of birth is required.")
	@Past
	@Column(name="DOB")
	private LocalDate dob;
	
	@NotNull(message = "Date of Joining is required.")
	@Column(name="DOJ")
	private LocalDate doj;
	
	@NotEmpty(message = "The gender is required.")
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="ACTIVE_SW")
	private Boolean activeSw;

}
