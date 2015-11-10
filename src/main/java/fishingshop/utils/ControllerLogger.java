package fishingshop.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Сергей on 21.09.2015.
 */

@Component
@Aspect
public class ControllerLogger {

    private static Logger logger=Logger.getLogger(ControllerLogger.class);

    @Before("execution(* fishingshop.controller.GoodsController..*(..))")
    public void logBeforeGoodsController(JoinPoint joinPoint) {
        logger.info("Start : " + joinPoint.getSignature().getName());
    }

    @After("execution(* fishingshop.controller.GoodsController..*(..))")
    public void logAfterGoodsController(JoinPoint joinPoint) {
        logger.info("End : " + joinPoint.getSignature().getName());
        logger.info("******");
    }

    @Before("execution(* fishingshop.controller.GroupsController..*(..))")
    public void logBeforeGroupsController(JoinPoint joinPoint) {
        logger.info("Start : " + joinPoint.getSignature().getName());
    }

    @After("execution(* fishingshop.controller.GroupsController..*(..))")
    public void logAfterGroupsController(JoinPoint joinPoint) {
        logger.info("End : " + joinPoint.getSignature().getName());
        logger.info("******");
    }

    @Before("execution(* fishingshop.controller.AdminTableController..*(..))")
    public void logBeforeTableController(JoinPoint joinPoint) {
        logger.info("Start : " + joinPoint.getSignature().getName());
    }

    @After("execution(* fishingshop.controller.AdminTableController..*(..))")
    public void logAfterTableController(JoinPoint joinPoint) {
        logger.info("End : " + joinPoint.getSignature().getName());
        logger.info("******");
    }


}
