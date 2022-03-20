package com.voronin.technicaltest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * The type Logger aspect.
 */
@Aspect
@Component
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Define pointcut for logger
     */
    @Pointcut("execution(public * com.voronin.technicaltest.dao..*(..)) " +
            "|| execution(public * com.voronin.technicaltest.service..*(..))" +
            "|| execution(public * com.voronin.technicaltest.exception..*(..))" +
            "|| execution(public * com.voronin.technicaltest.controller..*(..)) ")
    public void callAtLoggerAspectPublic() {
    }

    /**
     * Around call of {@link #callAtLoggerAspectPublic()} .
     *
     * @param call the Join Point
     * @return called method result
     * @throws Throwable if called method throws Throwable
     */
    @Around("callAtLoggerAspectPublic()")
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

            logger.info("Execution time of {}.{} :: {} ms. Arg: {} Result: {}",
                    methodSignature.getDeclaringType().getSimpleName(),
                    methodSignature.getName(),
                    clock.getTotalTimeMillis(),
                    Arrays.asList(call.getArgs()),
                    result);
        }

    }

}
