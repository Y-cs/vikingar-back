package self.vikingar.manager.record.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import self.vikingar.manager.record.ano.Record;
import self.vikingar.manager.record.factory.RecordSupportFactory;
import self.vikingar.manager.record.support.RecordSupport;

import java.util.Arrays;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 17:06
 * @Description:
 **/
@Aspect
@Slf4j
@ConditionalOnClass(RecordSupportFactory.class)
public class RecordAop {

    private final RecordSupportFactory supportFactory;

    public RecordAop(RecordSupportFactory supportFactory) {
        this.supportFactory = supportFactory;
    }

    @Pointcut("@annotation(self.vikingar.manager.record.ano.Record)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(record)")
    public Object advice(ProceedingJoinPoint joinPoint, Record record) throws Throwable {

        RecordSupport recordSupport = null;
        //结果
        Object result = null;
        //报错
        Throwable proceedException = null;
        try {
            recordSupport = supportFactory.create((MethodSignature) joinPoint.getSignature(), Arrays.asList(joinPoint.getArgs()), record);
            try {
                result = joinPoint.proceed();
                recordSupport.setResult(result);
            } catch (Throwable throwable) {
                recordSupport.setException(throwable);
                proceedException = throwable;
            }
            try {
                //持久化日志
                recordSupport.persistence();
            } catch (Exception e) {
                log.error("日志保存错误", e);
            }
        } finally {
            if (recordSupport != null) {
                //清理数据
                recordSupport.clear();
            }
        }
        if (proceedException != null) {
            //有错要暴露出去
            throw proceedException;
        }
        return result;
    }

}
