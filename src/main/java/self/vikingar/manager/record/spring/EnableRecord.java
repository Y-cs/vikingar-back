package self.vikingar.manager.record.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 9:51
 * @Description:
 **/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RecordRegistrar.class,RecordAop.class})
public @interface EnableRecord {



}
