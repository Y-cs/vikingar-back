package self.vikingar.manager.record.context;

import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 13:16
 * @Description:
 **/
public enum SpelContext {

    /**
     * 单例
     */
    INSTANCE;

    private final ThreadLocal<StandardEvaluationContext> THREAD_SPEL = new ThreadLocal<>();

    public StandardEvaluationContext getSpelContext() {
        return THREAD_SPEL.get();
    }

    public StandardEvaluationContext createContext(Object rootObject) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        standardEvaluationContext.setRootObject(rootObject);
        THREAD_SPEL.set(standardEvaluationContext);
        return standardEvaluationContext;
    }

    public void clear() {
        THREAD_SPEL.remove();
    }

}
