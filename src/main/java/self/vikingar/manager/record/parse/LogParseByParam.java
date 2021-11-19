package self.vikingar.manager.record.parse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.context.RecordContext;
import self.vikingar.manager.record.model.LogMessage;

import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:59
 * @Description:
 **/
@Slf4j
public class LogParseByParam extends LogParse {

    @Override
    public void init() {
    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        this.getSpelContext(logMessage);
        super.doNext(logMessage);
    }

    private void getSpelContext(LogMessage logMessage) {
        //从日志上下文获取Spel解析的上下文
        StandardEvaluationContext spelContext = super.getSpelContext();
        for (int i = 0; i < logMessage.getArgNames().length; i++) {
            spelContext.setVariable(logMessage.getArgNames()[i], logMessage.getArgs()[i]);
        }
        Map<String, Object> param = RecordContext.INSTANCE.getParam();
        if (param != null) {
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                spelContext.setVariable(entry.getKey(), entry.getValue());
            }
        }
    }


}
