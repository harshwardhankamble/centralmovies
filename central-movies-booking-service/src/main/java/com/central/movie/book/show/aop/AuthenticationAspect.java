package com.central.movie.book.show.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.central.book.common.exception.RuntimeServerException;
import com.central.book.common.exception.UnauthorizedException;
import com.central.book.common.message.Message;
import com.central.movie.book.show.security.AccessHandler;

@Aspect
@Configuration
public class AuthenticationAspect {

	@Autowired
	private AccessHandler accessHandler;
	
	@Before("within(com.central.book.show.controller..*)")
	public void authorizeUserBeforeExecution(JoinPoint joinPoint) {
		try {
			Integer userId = (Integer) joinPoint.getArgs()[0];
			MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
			Method method = methodSignature.getMethod();
			String[] roles = null;
			boolean accessAllowed = true;
			
			if (method.isAnnotationPresent(AccessControl.class)) {
				Annotation annotation = method.getAnnotation(AccessControl.class);
				AccessControl accessControl = (AccessControl) annotation;
				roles = accessControl.roles();
				
				accessAllowed = accessHandler.checkUserAccess(userId, roles);
			}
			
			if (!accessAllowed) {
				
				throw new UnauthorizedException(Message.UNAUTHROIZED_ACCESS);
			}
		} catch (ArrayIndexOutOfBoundsException | ClassCastException exception) {
			
			throw new RuntimeServerException("Please specify userid as first argument");
		} catch (Exception e) {
			
			throw e;
		}
	}
}
