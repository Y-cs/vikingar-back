package self.vikingar.manager.record.parse;

import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.context.SpelContext;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 16:32
 * @Description:
 **/
public abstract class LogParse {

    private LogRecordConfig config;

    private LogParse next;

    /**
     * 初始化
     * 由于配置的导入是在注册后 所以需要在被使用前使用配置中的某些项 需要有一个触发点
     * 这里我思考过可以用夫构造的方式初始化config,但是那样的话不仅降低了可读性整体比较混乱,并且如果真的有人需要扩展这里的话每次都需要传递config
     * ,放弃了那种做法,不如就设计一个初始化流程
     */
    public abstract void init();

    /**
     * 执行
     *
     * @param logMessage 责任链封装对象
     * @return bool 是否阻断继续执行
     */
    public abstract void doExecutor(LogMessage logMessage);

    public void setConfig(LogRecordConfig config) {
        this.config = config;
    }

    public void setNext(LogParse next) {
        this.next = next;
    }

    protected LogRecordConfig getConfig() {
        return config;
    }

    protected void doNext(LogMessage logMessage) {
        if (next != null) {
            next.doExecutor(logMessage);
        }
    }

    protected StandardEvaluationContext getSpelContext() {
        StandardEvaluationContext spelContext = SpelContext.INSTANCE.getSpelContext();
        if (spelContext == null) {
            spelContext = SpelContext.INSTANCE.createContext(getConfig().getSpelRootObject());
        }
        return spelContext;
    }

}
