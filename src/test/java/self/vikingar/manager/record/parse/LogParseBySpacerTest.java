package self.vikingar.manager.record.parse;

import org.junit.jupiter.api.Test;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:36
 * @Description:
 **/
class LogParseBySpacerTest extends LogParseBySpacer {
    {
        super.setConfig(new LogRecordConfig());
    }

    @Test
    void getSpacerValue1() {
        LogMessage logMessage = new LogMessage();
        logMessage.setOriginalMessage("这是一条奇怪的日志!?,参数1:`aaa`,参数2:`bbb``ccc`");
        super.handleSpacer(logMessage);
        System.out.println(logMessage);
        assert logMessage.getExpression().size() == 3;
    }

    @Test
    void getSpacerValue2() {
        LogMessage logMessage = new LogMessage();
        logMessage.setOriginalMessage("`bbb`");
        super.handleSpacer(logMessage);
        System.out.println(logMessage);
        assert "`bbb`".equals(logMessage.getExpression().get(0));
    }

    @Test
    void getSpacerValue3() {
        LogMessage logMessage = new LogMessage();
        logMessage.setOriginalMessage("`aaa`请求了,参数2:`bbb`");
        super.handleSpacer(logMessage);
        System.out.println(logMessage);
        assert logMessage.getExpression().size() == 2;
    }

    @Test
    void getSpacerValue4() {
        LogMessage logMessage = new LogMessage();
        logMessage.setOriginalMessage("参数1:`aaa`,参数2:`bbb`巴拉巴拉");
        super.handleSpacer(logMessage);
        System.out.println(logMessage);
        assert logMessage.getExpression().size() == 2;
    }
}