package self.vikingar.manager.record.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 9:48
 * @Description:
 **/
public enum RecordContext {

    /**
     * 中央参数单例
     */
    INSTANCE;

    private final ThreadLocal<Map<String, Object>> THREAD_PARAM = new ThreadLocal<>();

    public void addParam(String key, Object value) {
        Map<String, Object> map = Optional.ofNullable(THREAD_PARAM.get()).orElse(new HashMap<>());
        map.put(key, value);
        THREAD_PARAM.set(map);
    }

    public Map<String, Object> getParam() {
        Map<String, Object> map = THREAD_PARAM.get();
        return map == null ? Collections.EMPTY_MAP : map;
    }

    public void clear() {
        THREAD_PARAM.remove();
    }

}
