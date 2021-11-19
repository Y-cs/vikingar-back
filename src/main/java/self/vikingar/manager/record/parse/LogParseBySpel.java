package self.vikingar.manager.record.parse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.model.LogMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 14:09
 * @Description:
 **/
@Slf4j
public class LogParseBySpel extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        if (logMessage.getExpression() != null) {
            StandardEvaluationContext spelContext = super.getSpelContext();
            Map<String, Object> valueMap = new HashMap<>(logMessage.getExpression().size());
            for (String spel : logMessage.getExpression()) {
                valueMap.put(spel, doParseSpel(spel, spelContext));
            }
            logMessage.setValues(valueMap);
        }
        super.doNext(logMessage);
    }


    private Object doParseSpel(String spel, StandardEvaluationContext ctx) {
        try {
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(spel);
            return exp.getValue(ctx);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error("Spel解析错误:",e);
            }
            return null;
        }
    }

}
