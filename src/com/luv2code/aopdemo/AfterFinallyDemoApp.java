package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		get the beans from container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		
//		call method to find the accounts
		List<Account> theAccounts = null;
		
		try {
//			add a boolean flag to simulate an exception
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\n\nMain Program ... caught exception: " + e);
		}
		
//		display the accounts
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println("----");
		System.out.println(theAccounts);
		System.out.println("\n");
				
//		cose the context
		context.close();
	}

}
