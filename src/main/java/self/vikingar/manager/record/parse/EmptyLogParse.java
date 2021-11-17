package self.vikingar.manager.record.parse;

import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 18:18
 * @Description:
 **/
public class EmptyLogParse extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        doNext(logMessage);
    }
}
