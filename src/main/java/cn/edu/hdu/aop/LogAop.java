package cn.edu.hdu.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {

	private  static  final Logger logger = LoggerFactory.getLogger(LogAop. class);

	@Pointcut("execution (* cn.edu.hdu.controller..*.*(..))")  
	public  void controllerAspect() {  
	}

	
	 @AfterThrowing(pointcut = "controllerAspect()", throwing="e")  
	 public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		/* logger.error(e.toString());
		 logger.error(e.getLocalizedMessage());
		 logger.error(e.getMessage());*/
		 logger.error("error", e);
	 }
}
