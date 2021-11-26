package self.vikingar.manager.record.parse;


import self.vikingar.manager.record.context.ParseContext;
import self.vikingar.manager.record.support.SpacerSupport;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 10:20
 * @Description:
 **/
public class LogParseBySpacer extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(ParseContext parseContext) {
        this.handleSpacer(parseContext);
        super.doNext(parseContext);
    }

    /**
     * 拆解整个信息
     *
     * @param parseContext
     */
    protected void handleSpacer(ParseContext parseContext) {
        SpacerSupport spacerSupport = new SpacerSupport(getConfig().getSplit().getStartChar(), getConfig().getSplit().getStartChar());
        spacerSupport.doParse(parseContext.getMetaMessage());
        parseContext.setSubMessage(spacerSupport.getMessage());
        parseContext.setSpelExpression(spacerSupport.getExpression());
    }


}
