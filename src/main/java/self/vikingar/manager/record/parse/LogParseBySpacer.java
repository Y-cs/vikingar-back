package self.vikingar.manager.record.parse;

import self.vikingar.manager.record.model.LogMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:20
 * @Description: 用于提取日志参数
 **/
public class LogParseBySpacer extends LogParse {

    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        String originalMessage = logMessage.getOriginalMessage();
        List<String> spacerValue = getSpacerValue(originalMessage);
        logMessage.setExpression(spacerValue);
        super.doNext(logMessage);
    }

    protected List<String> getSpacerValue(String originalMessage) {
        List<String> expression = new ArrayList<>();
        int indexStart = -1, startTemp = 0, endTemp = 0;
        while (indexStart < originalMessage.length()) {
            if ((indexStart = startTemp = originalMessage.indexOf(getConfig().getParse().getStart(), indexStart + 1)) == -1) {
                break;
            }
            if ((indexStart = endTemp = originalMessage.indexOf(getConfig().getParse().getEnd(), indexStart + 1)) == -1) {
                break;
            }
            expression.add(originalMessage.substring(startTemp, endTemp + 1));
        }
        return expression;
    }

}
