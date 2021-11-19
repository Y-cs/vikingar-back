package self.vikingar.manager.record.parse;

import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.enums.RecordParamEnum;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 13:10
 * @Description:
 **/
public class LogParseByResult extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        this.getSpelContext(logMessage);
        super.doNext(logMessage);
    }

    private void getSpelContext(LogMessage logMessage) {
        StandardEvaluationContext spelContext = super.getSpelContext();
        if (logMessage.getException() != null) {
            spelContext.setVariable(RecordParamEnum.EXCEPTION.getKey(), logMessage.getException());
        }
        if (logMessage.getResult() != null) {
            spelContext.setVariable(RecordParamEnum.RESULT_PARAM.getKey(), logMessage.getResult());
        }
    }
}
