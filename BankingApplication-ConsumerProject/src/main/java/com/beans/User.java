package com.beans;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Data

public class User {
	
	private long accNo;
	private String name;
	private String mailId;
	private long mobileNo;
	private String ssn;
	private String typeOfAcc;
	private String password;
	

}
