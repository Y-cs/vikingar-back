package self.vikingar.manager.record.parse;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.model.LogMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:59
 * @Description:
 **/
public class LogParseBySpel extends LogParse {

    @Override
    public void init() {
    }

    @Override
    public void doExecutor(LogMessage logMessage) {
        Map<String, Object> value = new HashMap<>(logMessage.getExpression().size());
        if (logMessage.getExpression() != null) {
            StandardEvaluationContext ctx = new StandardEvaluationContext();
            ctx.setRootObject(getConfig().getSpelRootObject());
            for (int i = 0; i < logMessage.getArgNames().length; i++) {
                ctx.setVariable(logMessage.getArgNames()[i], logMessage.getArgs()[i]);
            }
            ctx.setVariable(getConfig().getResultParam(), logMessage.getResult());
            for (String spel : logMessage.getExpression()) {
                value.put(spel, doParseSpel(spel, ctx));
            }
        }
        logMessage.setValues(value);
        super.doNext(logMessage);
    }

    private Object doParseSpel(String spel, StandardEvaluationContext ctx) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(this.handleSpel(spel));
        return exp.getValue(ctx);
    }

    private String handleSpel(String spel) {
        return spel.replace(getConfig().getParse().getStart(), "")
                .replace(getConfig().getParse().getEnd(), "").trim();
    }

}
