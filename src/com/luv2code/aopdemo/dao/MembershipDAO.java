package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	
	public String addSillyMember() {
		System.out.println(getClass() + ": DOING STUFF: ADDING A MEMBERSHIP ACCOUNT");
		return "silly";
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": i am going to sleep now...");
	}

}
