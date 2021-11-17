package self.vikingar.manager.record.parse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.config.SpelRootObject;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 16:48
 * @Description:
 **/
public class LogParseManagerTest {
    private static LogParseManager logParseManager = null;

    @BeforeAll
    public static void before() {
        LogRecordConfig logRecordConfig = new LogRecordConfig();
        logRecordConfig.setParse(new LogRecordConfig.Parse());
        logRecordConfig.setSpelRootObject(new SpelRootObject() {
        });
        logParseManager = new LogParseManager(logRecordConfig);
        logParseManager.addParses(new LogParseBySpacer());
        logParseManager.addParses(new LogParseBySpel());
        logParseManager.addParses(new LogParseByAssemble());
    }

    @Test
    public void test1() {
        LogMessage logMessage = new LogMessage();
        logMessage.setOriginalMessage("这是一条日志,参数一:`#aa`,参数二:`#bb`");
        logMessage.setArgs(new Object[]{1, 2});
        logMessage.setArgNames(new String[]{"aa", "bb"});
        logParseManager.executor(logMessage);
        System.out.println(logMessage);
    }


}