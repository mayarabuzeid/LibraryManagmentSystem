package net.javaguides.lms.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerComponent {
    private static final Logger logger = LoggerFactory.getLogger(LoggerComponent.class);

    @Before("execution(* net.javaguides.lms..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                logger.info("Argument: " + arg);
            }
        }
    }

    @AfterReturning(pointcut = "execution(* net.javaguides.lms..*(..))", returning = "result")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: " + joinPoint.getSignature().getName());
        if (result != null) {
            logger.info("Return value: " + result);
        }
    }

    @AfterThrowing(pointcut = "execution(* net.javaguides.lms..*(..))", throwing = "error")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: " + joinPoint.getSignature().getName(), error);
    }
}
