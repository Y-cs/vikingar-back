package self.vikingar.manager.thread;

import lombok.extern.slf4j.Slf4j;
import self.vikingar.manager.GlobalConstant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/9 15:33
 * @Description: 默认的线程间上下文实现方案
 **/
@Slf4j
public class DefaultThreadContext implements ThreadContext {

    private static final ThreadLocal<Map<String, Object>> CACHE = new ThreadLocal<>();

    /**
     * 全局唯一
     */
    private DefaultThreadContext() {

    }

    @Override
    public Object get(GlobalConstant tokenCacheKey) {
        return get(tokenCacheKey.getConstant2String());
    }

    @Override
    public void put(GlobalConstant tokenCacheKey, Object value) {
        put(tokenCacheKey.getConstant2String(), value);
    }

    private static class ThreadContextInstance {
        protected static DefaultThreadContext threadContext = new DefaultThreadContext();
    }

    public static DefaultThreadContext getInstance() {
        return ThreadContextInstance.threadContext;
    }

    @Override
    public void put(String key, Object value) {
        Map<String, Object> map = Optional.ofNullable(CACHE.get()).orElse(new HashMap<>());
        Object oldValue = map.put(key, value);
        if (log.isDebugEnabled()) {
            if (oldValue == null) {
                log.debug("存入数据->key:{},value:{}", key, value);
            } else {
                log.debug("覆盖数据->key:{},oldValue:{},newValue:{}", key, oldValue, value);
            }
        }
        CACHE.set(map);
    }

    @Override
    public Object get(String key) {
        Object value = Optional.ofNullable(CACHE.get()).orElse(Collections.emptyMap()).get(key);
        log.debug("查询数据->key:{},value:{}", key, value);
        return value;
    }

    @Override
    public Object remove(String key) {
        Map<String, Object> map = CACHE.get();
        log.debug("删除数据->key:{}", key);
        if (map != null) {
            return map.remove(key);
        }
        return null;
    }

    @Override
    public void clear() {
        if (log.isDebugEnabled()) {
            log.debug("数据清空->map:{}", CACHE.get());
        }
        CACHE.remove();
    }

    @Override
    public boolean containKey(String key) {
        return get(key) != null;
    }

}
