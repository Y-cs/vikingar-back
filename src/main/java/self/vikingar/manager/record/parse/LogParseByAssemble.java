package self.vikingar.manager.record.parse;

import lombok.extern.slf4j.Slf4j;
import self.vikingar.manager.record.model.LogMessage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 16:44
 * @Description:
 **/
@Slf4j
public class LogParseByAssemble extends LogParse {


    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        setPullOffMessage(logMessage);
        setOperator(logMessage);
        super.doNext(logMessage);
    }

    private void setOperator(LogMessage logMessage) {
        logMessage.setOperator(getConfig().getSpelRootObject() == null ?
                getConfig().getDefaultOperator() :
                getConfig().getSpelRootObject().getOperator());
    }

    private void setPullOffMessage(LogMessage logMessage) {
        List<String> messages = logMessage.getMessages();
        List<String> expression = logMessage.getExpression();
        Iterator<String> expressionIterator = expression.iterator();
        Map<String, Object> values = logMessage.getValues();
        StringBuilder pullOffMsg = new StringBuilder();
        for (String message : messages) {
            pullOffMsg.append(message);
            if (expressionIterator.hasNext()) {
                pullOffMsg.append(values.get(expressionIterator.next()));
            }
        }
        logMessage.setPullOffMessage(pullOffMsg.toString());
    }
}
