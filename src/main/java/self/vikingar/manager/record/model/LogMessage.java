package self.vikingar.manager.record.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 15:38
 * @Description:
 **/
@Data
@Accessors(chain = true)
public class LogMessage {

    /**
     * 源信息
     */
    private String originalMessage;

    /**
     * 参数表达式
     */
    private List<String> expression;

    /**
     * 参数表达式对应的值
     */
    private Map<String, Object> values;

    /**
     * 处理后的信息
     */
    private String pullOffMessage;

    /**
     * 方法
     */
    private Method method;

    /**
     * 参数
     */
    private Object[] args;

    /**
     * 参数名称
     */
    private String[] argNames;

    /**
     * 执行结果
     */
    private Object result;

}
