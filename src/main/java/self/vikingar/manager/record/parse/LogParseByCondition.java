package self.vikingar.manager.record.parse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 16:27
 * @Description:
 **/
@Slf4j
public class LogParseByCondition extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        boolean condition = condition(logMessage);
        logMessage.setPrevent(!condition);
        if (condition) {
            super.doNext(logMessage);
        }
    }


    private boolean condition(LogMessage logMessage) {
        String condition = logMessage.getCondition();
        if (condition != null && condition.trim().length() > 0) {
            try {
                StandardEvaluationContext spelContext = super.getSpelContext();
                SpelExpressionParser parser = new SpelExpressionParser();
                Expression exp = parser.parseExpression(condition.trim());
                return Boolean.TRUE.equals(exp.getValue(spelContext, Boolean.class));
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    log.error("Spel解析错误:", e);
                }
                throw new RuntimeException("日志条件判断错误:" + e);
            }
        }
        return true;
    }

}
