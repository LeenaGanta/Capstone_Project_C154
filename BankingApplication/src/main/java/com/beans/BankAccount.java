package com.beans;

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
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class BankAccount {
	
	public BankAccount(User accNo) {
		super();
		this.accNo = accNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int balanceId;
	private double balance;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User accNo;
	

}
