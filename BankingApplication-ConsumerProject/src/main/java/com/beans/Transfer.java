package com.beans;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transfer {
	
	
	
	private long transferId;
	private User fromAccNo;
	
	private User toAccNo;
	private double amount;
	private LocalDateTime date;
	public Transfer(User fromAccNo, User toAccNo, double amount, LocalDateTime date) {
		super();
		this.fromAccNo = fromAccNo;
		this.toAccNo = toAccNo;
		this.amount = amount;
		this.date = date;
	}
	
	
 
}
