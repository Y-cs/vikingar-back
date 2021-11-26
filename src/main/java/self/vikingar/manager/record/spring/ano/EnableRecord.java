package self.vikingar.manager.record.spring.ano;

import org.springframework.context.annotation.Import;
import self.vikingar.manager.record.spring.aop.RecordAop;
import self.vikingar.manager.record.spring.factory.RecordFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 16:12
 * @Description:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({RecordFactory.class, RecordAop.class})
public @interface EnableRecord {
}
