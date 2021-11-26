package self.vikingar.manager.record.parse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import self.vikingar.manager.record.context.ParseContext;
import self.vikingar.manager.record.support.SpacerSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/26 11:25
 * @Description: 扩展字段的解析器
 **/
@Slf4j
public class LogParseByExtend extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(ParseContext parseContext) {
        SpacerSupport spacerSupport = new SpacerSupport(getConfig().getSplit().getStartChar(), getConfig().getSplit().getStartChar());
        spacerSupport.doParse(parseContext.getExtendAttribute());
        StandardEvaluationContext spelCtx = parseContext.getSpelCtx();
        List<Object> objs = new ArrayList<>();
        if (spelCtx != null) {
            try {
                for (String spel : spacerSupport.getExpression()) {
                    SpelExpressionParser parser = new SpelExpressionParser();
                    Expression exp = parser.parseExpression(spel);
                    objs.add(exp.getValue(spelCtx));
                }
            } catch (Exception e) {
                log.error("扩展字段解析失败", e);
                super.doNext(parseContext);
            }
        }
        Iterator<Object> objIterator = objs.iterator();
        StringBuilder sb = new StringBuilder();
        for (String message : spacerSupport.getMessage()) {
            sb.append(message);
            if (objIterator.hasNext()) {
                sb.append(objIterator.next());
            }
        }
        parseContext.setExtendAttribute(sb.toString());
        super.doNext(parseContext);
    }


}
