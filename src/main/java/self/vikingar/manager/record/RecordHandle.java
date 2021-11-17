package self.vikingar.manager.record;

import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.model.LogMessage;
import self.vikingar.manager.record.parse.LogParseManager;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 10:29
 * @Description:
 **/
public class RecordHandle {

    private final RecordPersistence persistence;

    private final LogRecordConfig config;

    private final LogParseManager parseManager;

    private final boolean autoParse;

    private LogRecord recordMaster;

    private LogMessage successLogMessage;

    private LogMessage failLogMessage;

    public RecordHandle(LogRecordConfig config, LogParseManager parseManager, RecordPersistence persistence, boolean autoParse) {
        this.config = config;
        this.parseManager = parseManager;
        this.persistence = persistence;
        this.autoParse = autoParse;
    }

    /**
     * 设置信息
     * 如果为前置解析 自动解析
     *
     * @param method       被执行方法
     * @param args         参数
     * @param recordMaster 日志注解主体
     */
    public void setRecordInfo(Method method, List<Object> args, LogRecord recordMaster) {
        this.recordMaster = recordMaster;

        Parameter[] parameters = method.getParameters();
        ArrayList<String> argNames = new ArrayList<>();
        for (Parameter parameter : parameters) {
            argNames.add(parameter.getName());
        }

        successLogMessage = new LogMessage().setOriginalMessage(recordMaster.success())
                .setMethod(method).setArgNames(argNames.toArray(new String[0])).setArgs(args.toArray());
        failLogMessage = new LogMessage().setOriginalMessage(recordMaster.fail())
                .setMethod(method).setArgNames(argNames.toArray(new String[0])).setArgs(args.toArray());

        if (autoParse && recordMaster.preParsing()) {
            //前置解析
            this.doParse();
        }

    }

    /**
     * 设置方法执行的结果
     * 如果为后置解析 自动解析
     *
     * @param result
     */
    public void setResult(Object result) {
        successLogMessage = Optional.ofNullable(successLogMessage).orElse(new LogMessage()).setResult(result);
        failLogMessage = Optional.ofNullable(failLogMessage).orElse(new LogMessage()).setResult(result);

        if (autoParse && !recordMaster.preParsing()) {
            //后置解析
            this.doParse();
        }
    }

    /**
     * 解析
     */
    public void doParse() {
        if (successLogMessage != null) {
            parseManager.executor(this.successLogMessage);
        }
        if (failLogMessage != null) {
            parseManager.executor(this.failLogMessage);
        }
    }

    public void doPersistence(boolean success) {
        if (success) {
            doSave(successLogMessage);
        } else {
            doSave(failLogMessage);
        }
    }

    private void doSave(LogMessage successLogMessage) {

    }

}
