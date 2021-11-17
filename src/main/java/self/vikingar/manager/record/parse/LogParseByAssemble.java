package self.vikingar.manager.record.parse;

import self.vikingar.manager.record.model.LogMessage;

import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 16:44
 * @Description:
 **/
public class LogParseByAssemble extends LogParse {


    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        String pullOffMessage = logMessage.getOriginalMessage();
        for (Map.Entry<String, Object> entry : logMessage.getValues().entrySet()) {
            pullOffMessage = pullOffMessage.replace(entry.getKey(), String.valueOf(entry.getValue()));
        }
        logMessage.setPullOffMessage(pullOffMessage);
        super.doNext(logMessage);
    }
}
