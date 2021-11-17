package self.vikingar.manager.record.ano;

import self.vikingar.manager.record.enums.RecordEnum;

import java.lang.annotation.*;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 9:55
 * @Description:
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {

    String success();

    String fail() default "";

    RecordEnum recordType();

    /**
     * 前置处理 某些场景下会修改入参 如果后置解析的话会导致取到的是方法执行结束后的结果
     * 可以选择前置处理
     * 但使用前置处理无法再使用result来获取执行后的内容了
     *
     * @return
     */
    boolean preParsing() default false;

}
