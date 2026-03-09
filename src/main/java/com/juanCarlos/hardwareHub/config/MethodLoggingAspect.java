package com.juanCarlos.hardwareHub.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.StringJoiner;

@Aspect
@Component
public class MethodLoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(MethodLoggingAspect.class);
    private static final int MAX_LOG_VALUE_LENGTH = 200;

    @Pointcut("execution(public * com.juanCarlos.hardwareHub.controller..*(..)) || execution(public * com.juanCarlos.hardwareHub.service.implementation..*(..))")
    public void monitoredMethods() {
    }

    @Around("monitoredMethods()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        String argsText = formatArguments(joinPoint.getArgs());

        LOGGER.info("INICIO {} args={}", methodName, argsText);

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            LOGGER.info("EXITO {} tiempoMs={} resultado={}", methodName, duration, formatValue(result));
            return result;
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - startTime;
            LOGGER.error(
                    "ERROR {} tiempoMs={} tipo={} mensaje={}",
                    methodName,
                    duration,
                    ex.getClass().getSimpleName(),
                    ex.getMessage(),
                    ex
            );
            throw ex;
        }
    }

    private String formatArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return "[]";
        }

        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (Object arg : args) {
            joiner.add(formatValue(arg));
        }
        return joiner.toString();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof byte[] bytes) {
            return "[byte[] length=" + bytes.length + "]";
        }

        if (value instanceof Collection<?> collection) {
            return "[Collection size=" + collection.size() + "]";
        }

        Class<?> valueClass = value.getClass();
        if (valueClass.isArray()) {
            return "[Array length=" + Array.getLength(value) + "]";
        }

        String text = String.valueOf(value);
        if (text.length() > MAX_LOG_VALUE_LENGTH) {
            return text.substring(0, MAX_LOG_VALUE_LENGTH) + "...";
        }
        return text;
    }
}
