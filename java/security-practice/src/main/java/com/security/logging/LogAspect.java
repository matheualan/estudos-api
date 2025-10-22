package com.security.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public LogAspect(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String dataHour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String method = request.getMethod();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("[INIT] {} {} {}.{}() Args: {}",
                method, dataHour, className, methodName, Arrays.toString(args));

        long initCount = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - initCount;

            log.info("[END] {} Status: {} {}.{}() Time: {} ms",
                    method, response.getStatus(), className, methodName, duration);

            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - initCount;
            log.error("[ERROR] {} {}.{}() Time: {} ms - Message: {}",
                    method, className, methodName, duration, e.getMessage(), e);
            throw e;
        }
    }

}
