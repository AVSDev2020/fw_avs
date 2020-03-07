package com.fw.amazon.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {
	
	@Pointcut("execution(public * com.fw.amazon.controller..*.*(..))")
	public void logController() {		
	}
	
	@Pointcut("execution(public * com.fw.amazon.service..*.*(..))")
	public void logService() {		
	} 
	
	@Before("logController()")
	public void doBefore(JoinPoint joinPoint){
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			LogRequest logRequest = new LogRequest(System.currentTimeMillis(),
					request.getRequestURL().toString(),
					request.getMethod(),
					joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
					JSON.toJSONString(joinPoint.getArgs()));
			if(log.isDebugEnabled()) { 
				log.debug(JSON.toJSONString(logRequest)); 
			}
		}catch(Exception ex) {
			log.error("LogAspect exception: ", ex.getMessage());
		}
		
	}
	
	@AfterReturning(returning = "ret", pointcut = "logController()")
	public void doAfterReturning(Object ret) {
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			LogResponse logResponse = new LogResponse(System.currentTimeMillis(),
					response.getStatus(),
					JSON.toJSONString(ret));
			if(log.isDebugEnabled()) { 
				log.debug(JSON.toJSONString(logResponse));
			}
		} catch(Exception ex) {
			log.error("LogAspect exception: ", ex.getMessage());
		}
	}
	
	@Around("logService()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		long time = System.currentTimeMillis();
		try {
			Signature signature = joinPoint.getSignature();	
			String serviceName = signature.getDeclaringTypeName() + "." + signature.getName();
			LogService logService = new LogService(time, 
					serviceName, 
					JSON.toJSONString(joinPoint.getArgs()), null);
			if(log.isDebugEnabled()) { 
				log.debug(JSON.toJSONString(logService));
			}
			result = joinPoint.proceed();			
			time = System.currentTimeMillis();
			logService = new LogService(time, serviceName, null, JSON.toJSONString(result));
			if(log.isDebugEnabled()) {
				log.debug(JSON.toJSONString(logService));
			}
			return result;
		} catch (Exception ex) {
			log.error("LogAspect exception: ", ex.getMessage());
			//ex.printStackTrace();
		}
		return result;
	}
	
	@Data
	@AllArgsConstructor
	class LogRequest {
		private long tracedId;
		private String url;
		private String httpMethod;
		private String methodName;
		private String params;
	}
	
	@Data
	@AllArgsConstructor
	class LogResponse {
		private long tracedId;
		private int status;
		private String params;
	}
	
	@Data
	@AllArgsConstructor
	class LogService {
		private long tracedId;
		private String methodName;
		private String params;	
		private String result;
	}

}
