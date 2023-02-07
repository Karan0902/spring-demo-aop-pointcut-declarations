package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
//		read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
//		get the beans from container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
//		get the membership bean from spring container
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
//		call the business method	
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		System.out.println("\n");
		theAccountDAO.doWork();
		System.out.println("\n");
		
//		call the account dao getter/setter methods
		theAccountDAO.setName("foobar");
		System.out.println("\n");
		theAccountDAO.setServiceCode("silver");
		System.out.println("\n");
		theAccountDAO.getName();
		System.out.println("\n");
		theAccountDAO.getServiceCode();
		System.out.println("\n");
		
//		call the membership business method
		membershipDAO.addSillyMember();
		System.out.println("\n");
		membershipDAO.goToSleep();
		System.out.println("\n");
				
//		cose the context
		context.close();
	}

}
