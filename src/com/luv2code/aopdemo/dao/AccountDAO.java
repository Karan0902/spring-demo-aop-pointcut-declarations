package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;

//	add new method: findAccounts()
	public List<Account> findAccounts(){
		
		List<Account> myAccounts = new ArrayList<>();
		
//		create sample accounts
		Account account1 = new Account("John", "Silver");
		Account account2 = new Account("Madhu", "Platinum");
		Account account3 = new Account("Luca", "Gold");
		
//		add them to our accounts list
		myAccounts.add(account1);
		myAccounts.add(account2);
		myAccounts.add(account3);
		
		return myAccounts;
	}
	
	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": in setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return false;
	}
}
