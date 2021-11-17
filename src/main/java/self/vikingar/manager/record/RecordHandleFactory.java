package self.vikingar.manager.record;

import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.parse.LogParseByAssemble;
import self.vikingar.manager.record.parse.LogParseBySpacer;
import self.vikingar.manager.record.parse.LogParseBySpel;
import self.vikingar.manager.record.parse.LogParseManager;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/17 17:37
 * @Description:
 **/
public class RecordHandleFactory {

    private final RecordPersistence persistence;

    private final LogRecordConfig config;

    private final LogParseManager parseManager;

    private volatile boolean autoParse = false;

    public RecordHandleFactory(LogRecordConfig config) {
        this(config, null);
    }

    public RecordHandleFactory(LogRecordConfig config, LogParseManager parseManager) {
        this(config, parseManager, null);
    }

    public RecordHandleFactory(LogRecordConfig config, LogParseManager parseManager, RecordPersistence persistence) {
        if (parseManager == null) {
            parseManager = new LogParseManager(config);
            parseManager.addParses(new LogParseBySpacer());
            parseManager.addParses(new LogParseBySpel());
            parseManager.addParses(new LogParseByAssemble());
        }
        if (persistence == null) {
            persistence = config.getRecordPersistence();
        }
        this.config = config;
        this.parseManager = parseManager;
        this.persistence = persistence;
    }


    public RecordHandle create(Method method, List<Object> args, LogRecord logRecord) {
        RecordHandle recordHandle = new RecordHandle(config, parseManager, persistence, autoParse);
        recordHandle.setRecordInfo(method, args, logRecord);
        return recordHandle;
    }

    /**
     * 是否自动解析 在create时和setResult时
     * 自动解析可能会造成不解析:
     *      1.先设置后置解析
     *      2.create->不解析
     *      3.设置前置解析
     *      4.setResult->不解析
     * @param autoParse
     */
    public void setAutoParse(boolean autoParse) {
        this.autoParse = autoParse;
    }

}
