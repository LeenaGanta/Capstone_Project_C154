package com.beans;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	@OneToOne
	private User fromAccNo;
	@OneToOne
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
