package self.vikingar.manager.record.parse;

import lombok.Data;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 16:53
 * @Description: 解析器的管理类
 **/
@Data
public class LogParseManager {

    private final LogRecordConfig config;

    /**
     * 头节点
     */
    private final LogParse head = new EmptyLogParse();

    /**
     * 当前最新节点
     */
    private volatile LogParse nowPoint;

    public LogParseManager(LogRecordConfig config) {
        this.config = config;
        nowPoint = head;
    }

    /**
     * 添加解析方案
     * 写的场景太少没必要保证并发安全  只是要保证读写间的问题就好了 上个独占锁哈哈哈哈哈哈哈
     *  此处设计的考虑
     *      责任链可以通过集合或者doNext两种方式实现
     *      如果是需要在其中找到一个能处理的来处理使用集合更合适
     *      如果是想要每一个都进行部分处理可以选择doNext方式,其次doNext如果不是返回void的话可能会有爆栈风险
     * @param logParse
     */
    public synchronized void addParses(LogParse logParse) {
        logParse.setConfig(this.config);
        logParse.init();
        nowPoint.setNext(logParse);
        nowPoint = logParse;
    }

    /**
     * 执行解析
     *
     * @param logMessage
     */
    public void executor(LogMessage logMessage) {
        head.doExecutor(logMessage);
    }

    public LogRecordConfig getConfig() {
        return config;
    }

}
