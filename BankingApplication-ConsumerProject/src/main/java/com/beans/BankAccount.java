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
public class BankAccount {
	
	public BankAccount(User accNo) {
		super();
		this.accNo = accNo;
	}

	
	private int balanceId;
	private double balance;
	private User accNo;
	

}
