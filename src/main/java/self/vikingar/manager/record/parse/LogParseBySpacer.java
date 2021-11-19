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
        handleSpacer(logMessage);
        super.doNext(logMessage);
    }

    /**
     * 拆解整个信息
     *
     * @param logMessage
     */
    protected void handleSpacer(LogMessage logMessage) {
        String originalMessage = logMessage.getOriginalMessage();
        List<String> message = new ArrayList<>();
        List<String> expression = new ArrayList<>();
        int indexTemp = -1, indexStart = -1, startTemp = 0, endTemp = 0;
        while (indexStart < originalMessage.length()) {
            indexTemp = indexStart + 1;
            if ((indexStart = startTemp = originalMessage.indexOf(getConfig().getParse().getStart(), indexStart + 1)) == -1) {
                break;
            }
            if ((indexStart = endTemp = originalMessage.indexOf(getConfig().getParse().getEnd(), indexStart + 1)) == -1) {
                break;
            }
            message.add(originalMessage.substring(indexTemp, startTemp));
            expression.add(this.handleSpel(originalMessage.substring(startTemp, endTemp + 1)));
        }
        message.add(originalMessage.substring(indexTemp, originalMessage.length()));
        logMessage.setMessages(message);
        logMessage.setExpression(expression);
    }

    private String handleSpel(String spel) {
        return spel.substring(getConfig().getParse().getStart().length(),
                        spel.length() - getConfig().getParse().getEnd().length())
                .trim();
    }

}
