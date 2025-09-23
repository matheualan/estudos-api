package br.com.securitystudy.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private static final DateTimeFormatter BR_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

//    private final HttpServletRequest request;
//    private final HttpServletResponse response;

//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void controllerMethods() {
//    }

    @Pointcut("execution(public * (@org.springframework.web.bind.annotation.RestController *).*(..))")
    public void controllerMethods() { }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        String dateHour = LocalDateTime.now().format(BR_FORMAT);
        String methodHttp = request.getMethod();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("[INIT] {} {} {}.{}() {}",
                dateHour, methodHttp, className, methodName, Arrays.toString(args));

        long initCount = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - initCount;

            log.info("[END] {} {}.{}() Status: {} Time: {} ms",
                    methodHttp, className, methodName, response.getStatus(), duration);

            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - initCount;
            log.error("[ERROR] {} {}.{}() Time: {} ms Message: {}",
                    methodHttp, className, methodName, duration, e.getMessage(), e);
            throw e;
        }
    }

}