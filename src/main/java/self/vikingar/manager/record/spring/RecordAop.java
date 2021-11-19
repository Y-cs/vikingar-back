package self.vikingar.manager.record.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import self.vikingar.manager.record.RecordHandle;
import self.vikingar.manager.record.RecordHandleFactory;
import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.context.RecordContext;
import self.vikingar.manager.record.context.SpelContext;

import java.util.Arrays;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 18:00
 * @Description:
 **/
@Aspect
@Slf4j
@ConditionalOnClass(RecordHandleFactory.class)
public class RecordAop {

    private final RecordHandleFactory recordHandleFactory;

    public RecordAop(RecordHandleFactory recordHandleFactory) {
        this.recordHandleFactory = recordHandleFactory;
        recordHandleFactory.setAutoParse(true);
    }

    @Pointcut("@annotation(self.vikingar.manager.record.ano.LogRecord)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(logRecord)")
    public Object advice(ProceedingJoinPoint joinPoint, LogRecord logRecord) throws Throwable {
        //结果
        Object result = null;
        //报错
        Throwable proceedException = null;
        try {
            //创建处理对象
            RecordHandle recordHandle = recordHandleFactory.create((MethodSignature) joinPoint.getSignature(),
                    Arrays.asList(joinPoint.getArgs()), logRecord);
            try {
                //执行业务逻辑
                result = joinPoint.proceed();
            } catch (Throwable e) {
                //报错
                proceedException = e;
                recordHandle.setException(e);
            }
            try {
                //日志处理
                recordHandle.setResult(result);
                recordHandle.doPersistence();
            } catch (Exception e) {
                log.error("日志保存错误", e);
            }
        } finally {
            RecordContext.INSTANCE.clear();
            SpelContext.INSTANCE.clear();
        }
        if (proceedException != null) {
            //有错要暴露出去
            throw proceedException;
        }
        return result;
    }


}
