//package br.com.springboot.essentials2.logging;
//
//import br.com.springboot.essentials2.util.DateUtil;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Slf4j
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class LogAspect {
//
//    private final HttpServletRequest request;
//    private final HttpServletResponse response;
//
//    @Pointcut("with(@org.springframework.web.bind.annotation.RestController *)")
//    public void controllerMethods() {
//    }
//
//    @Around("controllerMethods()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        String dateHour = DateUtil.dateFormatterBR(LocalDateTime.now());
//        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
//        String controllerMethod = joinPoint.getSignature().getName();
//        String httpMethod = request.getMethod();
//        Object[] args = joinPoint.getArgs();
//
//        log.info("[INIT] {} {} {}.{}() Args: {}",
//                dateHour,httpMethod, controllerName, controllerMethod, Arrays.toString(args));
//
//        long countInit = System.currentTimeMillis();
//
//        try {
//            Object result = joinPoint.proceed();
//
//            long duration = System.currentTimeMillis() - countInit;
//
//            log.info("[END] {} {}.{}() Status: {} Time: {} ms",
//                    httpMethod, controllerName, controllerMethod, response.getStatus(), duration);
//
//            return result;
//        } catch (Exception e) {
//            long duration = System.currentTimeMillis() - countInit;
//
//            log.error("[ERROR] {} {}.{}() Time: {} ms Message: {}",
//                    httpMethod, controllerName, controllerMethod, duration, e.getMessage(), e);
//
//            throw e;
//        }
//    }
//
//}
