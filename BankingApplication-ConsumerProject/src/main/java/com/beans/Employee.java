package com.beans;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Employee {
	
	
	private int empId;
	private String name;
	private String password;
	private long mobileNo;
	private String mailId;
	

}
