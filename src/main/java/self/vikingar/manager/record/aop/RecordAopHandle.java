package self.vikingar.manager.record.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 11:03
 * @Description:
 **/
@Aspect
@Component
public class RecordAopHandle {

    @Pointcut("@annotation(self.vikingar.manager.record.ano.LogRecord)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            //执行业务逻辑
            result = joinPoint.proceed();
        } catch (Throwable e) {
            //报错

        }
        return result;
    }

}
