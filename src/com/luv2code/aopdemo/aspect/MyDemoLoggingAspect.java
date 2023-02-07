package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
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
