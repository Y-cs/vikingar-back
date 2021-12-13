package self.vikingar.manager.record.factory;


import self.vikingar.manager.record.config.RecordConfig;
import self.vikingar.manager.record.context.ParseContext;
import self.vikingar.manager.record.parse.*;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 18:09
 * @Description: 解析功能责任链工厂
 **/
public class RecordParseFactory {

    private final RecordConfig recordConfig;

    private final LogParse head = new LogParseByEmpty();

    @SuppressWarnings("java:S3077")
    private volatile LogParse point = head;

    public RecordParseFactory(RecordConfig recordConfig) {
        this.recordConfig = recordConfig;

        this.addParse(new LogParseBySpacer());
        this.addParse(new LogParseBySpel());
        this.addParse(new LogParseByExtend());
        this.addParse(new LogParseByAssemble());
    }

    public LogParse getParse() {
        return head;
    }

    /**
     * 添加解析
     *
     * @param logParse 解析器
     */
    public synchronized void addParse(LogParse logParse) {
        logParse.setConfig(this.recordConfig);
        logParse.init();
        this.point.setNext(logParse);
        this.point = logParse;
    }

    /**
     * 执行
     *
     * @param parseContext 上下文
     */
    public void doExecutor(ParseContext parseContext) {
        this.head.doExecutor(parseContext);
    }


}
