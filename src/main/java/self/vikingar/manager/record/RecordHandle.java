package self.vikingar.manager.record;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.reflect.MethodSignature;
import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.context.RecordContext;
import self.vikingar.manager.record.context.SpelContext;
import self.vikingar.manager.record.enums.ActiveCycleEnum;
import self.vikingar.manager.record.model.LogMessage;
import self.vikingar.manager.record.parse.LogParseManager;
import self.vikingar.manager.record.persistence.RecordPersistence;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;


/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 10:29
 * @Description:
 **/
@Slf4j
public class RecordHandle {

    private final RecordPersistence persistence;

    private final LogRecordConfig config;

    private final LogParseManager parseManager;

    private final boolean autoParse;

    private LogRecord recordMaster;

    private LogMessage successLogMessage;

    private LogMessage failLogMessage;

    private ActiveCycleEnum cycle;

    public RecordHandle(LogRecordConfig config, LogParseManager parseManager, RecordPersistence persistence, boolean autoParse) {
        this.config = config;
        this.parseManager = parseManager;
        this.persistence = persistence;
        this.autoParse = autoParse;
        changeCycle(ActiveCycleEnum.NOT_RUN);
    }

    /**
     * 设置信息
     * 如果为前置解析 自动解析
     *
     * @param methodSignature 被执行方法
     * @param args            参数
     * @param recordMaster    日志注解主体
     */
    public void setRecordInfo(MethodSignature methodSignature, List<Object> args, LogRecord recordMaster) {
        changeCycle(ActiveCycleEnum.NOT_RUN);
        this.recordMaster = recordMaster;
        Method method = methodSignature.getMethod();
        String[] argNames = methodSignature.getParameterNames();
        successLogMessage = new LogMessage().setOriginalMessage(recordMaster.success())
                .setMethod(method).setArgNames(argNames).setArgs(args.toArray())
                .setCondition(recordMaster.condition());
        failLogMessage = new LogMessage().setOriginalMessage(recordMaster.fail())
                .setMethod(method).setArgNames(argNames).setArgs(args.toArray())
                .setCondition(recordMaster.condition());
        doAutoParse();
    }

    /**
     * 设置方法执行的结果
     *
     * @param result
     */
    public void setResult(Object result) {
        changeCycle(ActiveCycleEnum.NORMAL_END);
        successLogMessage = Optional.ofNullable(successLogMessage).orElse(new LogMessage()).setResult(result);
        failLogMessage = Optional.ofNullable(failLogMessage).orElse(new LogMessage()).setResult(result);
    }

    /**
     * 设置错误
     *
     * @param throwable
     */
    public void setException(Throwable throwable) {
        if (throwable != null) {
            changeCycle(ActiveCycleEnum.EXCEPTION);
        }
        failLogMessage = Optional.ofNullable(failLogMessage).orElse(new LogMessage()).setException(throwable);
    }

    /**
     * 解析
     */
    public void doParse() {
        if (recordMaster.success() != null && recordMaster.success().length() > 0 && successLogMessage != null
                && cycle != ActiveCycleEnum.EXCEPTION) {
            parseManager.executor(this.successLogMessage);
        }
        if (recordMaster.fail() != null && recordMaster.fail().length() > 0 && failLogMessage != null
                && cycle == ActiveCycleEnum.EXCEPTION) {
            parseManager.executor(this.failLogMessage);
        }
    }

    /**
     * 持久化
     */
    public void doPersistence() {
        doAutoParse();
        if (cycle != ActiveCycleEnum.EXCEPTION) {
            successLogMessage.setOperationResults(true);
            doSave(successLogMessage);
        } else {
            successLogMessage.setOperationResults(false);
            doSave(failLogMessage);
        }
    }

    private void doSave(LogMessage logMessage) {
        log.info("操作人:{},操作内容:{}", logMessage.getOperator(), logMessage.getPullOffMessage());
        if (logMessage.isPrevent()) {
            log.info("持久化操作被阻止");
        } else {
            persistence.save(logMessage, recordMaster);
        }
    }

    public void clear() {
        RecordContext.INSTANCE.clear();
        SpelContext.INSTANCE.clear();
    }

    private void changeCycle(ActiveCycleEnum cycle) {
        if (this.cycle == null || cycle.ordinal() > this.cycle.ordinal()) {
            //修改状态的index大于当前状态允许修改
            this.cycle = cycle;
        }
    }

    private void doAutoParse() {
        if (autoParse) {
            if (recordMaster.preParsing()) {
                //前置解析  必须是在 没有运行和运行中
                if (cycle == ActiveCycleEnum.NOT_RUN || cycle == ActiveCycleEnum.IS_RUN) {
                    doParse();
                }
            } else {
                //后置解析  必须是在 错误或者正常结束时
                if (cycle == ActiveCycleEnum.NORMAL_END || cycle == ActiveCycleEnum.EXCEPTION) {
                    doParse();
                }
            }
        }
    }

}
