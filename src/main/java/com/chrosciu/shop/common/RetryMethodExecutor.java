package com.chrosciu.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class RetryMethodExecutor {
    @Around("@annotation(retry)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint, Retry retry) throws Throwable {
        var currentAttempt = 0;
        Throwable throwable;
        do {
            currentAttempt++;
            log.info("{}.{} execution attempt {}",
                    proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(),
                    proceedingJoinPoint.getSignature().getName(),
                    currentAttempt);
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable t) {
                throwable = t;
            }
        } while (currentAttempt < retry.attempts());
        throw throwable;
    }
}
