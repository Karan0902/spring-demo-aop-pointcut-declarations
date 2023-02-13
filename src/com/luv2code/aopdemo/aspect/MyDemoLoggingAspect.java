package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
//		print out method we are advising on
		String method = proceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @Around on method: " + method);

//		get begin timestamp
		long begin = System.currentTimeMillis();
		Object resultObject = null;
		try {
//			now, lets execute the method
			resultObject = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			
//			log the exception
			System.out.println("warning--->" + e.getMessage());
			
//			rethrow the exception
			throw e;
		}
		
//		get end timestamp
		long end = System.currentTimeMillis();
		
//		compute duration and display it
		long duration = end - begin;
		System.out.println("\n====>From the around advice... The time take for execution is: " + duration/1000);
		
		return resultObject;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		
//		print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @After (Finally) on method: " + method);

	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowingFindaccountsAdvice(
			JoinPoint joinPoint, Throwable theExc) {
		
//		print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterThrowing on method: " + method);
		
//		log the exception
		System.out.println("====>>> The exception is: " + theExc);
		
	}
	
	
//	add a new advice for afterReturning on findAccounts method
	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
					returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
//		print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterReturning on method: " + method);
		
		convertAccountNamesToUpperCase(result);
		
//		print out the results of method call
//		if(!result.isEmpty()) {
//			Account tempAccount = result.get(0);
//			tempAccount.setName("Daffy Duck");
//		}
		System.out.println("\n====>>> Result is: " + result);
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for(Account tempAccount : result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
	}

	@Before("com.luv2code.aopdemo.aspect.PointcutAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("====>>>> Executing @Before advice on addAccount()");
		
//		display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("===> The method signature is: " + methodSignature);
		
//		display the method args
		Object[] argsObjects = joinPoint.getArgs();
		for(Object argObject : argsObjects) {
			System.out.println("The arguments for this method are: "+ argObject);
			
			if(argObject instanceof Account) {
				Account theAccount = (Account) argObject;
				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());
			}
		}
	} 
}
