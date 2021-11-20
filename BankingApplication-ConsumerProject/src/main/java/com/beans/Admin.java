package com.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
	
	
	private int adminId;
	private String name;
	private String mailId;
	private String mobileNo;
	private String password;

}
