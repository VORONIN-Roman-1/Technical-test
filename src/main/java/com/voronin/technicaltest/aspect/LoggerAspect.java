package com.voronin.technicaltest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.voronin.technicaltest.dao..*(..)) " +
            "|| execution(public * com.voronin.technicaltest.service..*(..))" +
            "|| execution(public * com.voronin.technicaltest.exception..*(..))" +
            "|| execution(public * com.voronin.technicaltest.controller..*(..))  ")
    public void callAtMyServicePublic() {
    }

    @Around("callAtMyServicePublic()")
    public Object aroundCallAt(ProceedingJoinPoint call) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) call.getSignature();
        StopWatch clock = new StopWatch(call.toString());
        Object result = null;
        try {
            clock.start(call.toShortString());
            result = call.proceed();
            return result;
        } finally {
            clock.stop();
            logger.info("Execution time of "
                    + methodSignature.getDeclaringType().getSimpleName() // Class Name
                    + "." + methodSignature.getName() + " " // Method Name
                    + ":: " + clock.getTotalTimeMillis() + " ms." + " Arg: " + Arrays.asList(call.getArgs()) + " Result: " + result);
        }

    }

}