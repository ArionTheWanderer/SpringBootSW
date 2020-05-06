package ru.itis.springsem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;
import ru.itis.springsem.security.details.UserDetailsImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @AfterReturning(pointcut = "@annotation(org.springframework.web.bind.annotation.GetMapping)",
            returning = "result")
    public void logControllersAfterReturning(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "Метод " + methodName + " вернул страницу: " + result.toString());
    }

    @AfterReturning(pointcut = "execution(public * ru.itis.springsem.security.details.UserDetailsServiceImpl.loadUserByUsername(..))",
    returning = "result")
    public void logLoadUserByEmail(JoinPoint jp, UserDetailsImpl result) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "Метод " + methodName + " загрузил пользователя: email = "
                + result.getUser().getEmail() + ", firstName = " + result.getUser().getFirstName() +
                ", lastName = " + result.getUser().getLastName());
    }

    @AfterThrowing(pointcut = "execution(public * ru.itis.springsem.security.details.UserDetailsServiceImpl.loadUserByUsername(..))")
    public void logFailedLoadUserByEmail(JoinPoint jp) {
        String email = (String)jp.getArgs()[0];
        String methodName = jp.getSignature().getName();
        logger.log(Level.SEVERE, "Метод " + methodName + " не нашел пользователя: email = "
                + email);
    }

    @AfterReturning(pointcut = "@annotation(ru.itis.springsem.aspect.LoggableServiceMethod)",
            returning = "result")
    public void logServiceAfterReturning(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "Метод " + methodName +
                " вернул значение: " + result.toString());
    }

    @AfterThrowing(pointcut = "@annotation(ru.itis.springsem.aspect.LoggableServiceMethod)",
            throwing = "ex")
    public void logServiceAfterReturning(JoinPoint jp, Throwable ex) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "Метод " + methodName +
                " бросил исключение: " + ex.toString() + "(" + ex.getMessage() + ")");
    }
}
