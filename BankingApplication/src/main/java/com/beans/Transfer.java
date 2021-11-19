package com.beans;

import java.time.LocalDateTime;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Transfer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transferId;
	@OneToOne(cascade = CascadeType.ALL)
	private User fromAccNo;
	@OneToOne(cascade = CascadeType.ALL)
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
