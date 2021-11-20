package com.beans;


import java.time.LocalDateTime;



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
public class Transaction {
	
	
	private long txnId;
	private User accNo;
	private String typeOfTransaction;
	private double amount;
	private LocalDateTime date;

}
