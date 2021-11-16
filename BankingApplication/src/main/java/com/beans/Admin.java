package com.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adminId;
	private String name;
	private String mailId;
	private String mobileNo;
	private String password;

}
