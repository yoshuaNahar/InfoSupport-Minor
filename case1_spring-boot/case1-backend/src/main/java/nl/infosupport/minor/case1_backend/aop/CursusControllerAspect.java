package nl.infosupport.minor.case1_backend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CursusControllerAspect {

  @Around("within(nl.infosupport.minor.case1_backend.*.*) && execution(public * *(..))")
  public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
    Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    logger.debug(joinPoint.getSignature().getName() + "() called");
    long currentTicks = System.currentTimeMillis();
    try {
      Object result = joinPoint.proceed();
      logger.debug(joinPoint.getSignature().getName() + "() executed in " +
          Long.toString(System.currentTimeMillis() - currentTicks), "ms");
      return result;
    } catch (Throwable ex) {
      logger.debug(joinPoint.getSignature().getName() + "() failed");
      throw ex;
    }
  }

}
