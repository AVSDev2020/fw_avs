package com.fw.amazon.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
	public void log() {	
	}
	
	@Before("log()")
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
	
	@AfterReturning(returning = "ret", pointcut = "log()")
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
		private String args;
	}

}
