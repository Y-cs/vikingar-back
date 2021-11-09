package self.vikingar.manager.thread;

import self.vikingar.config.constant.GlobalConstant;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/9 16:06
 * @Description: 线程间上下文
 **/
public interface ThreadContext {

    /**
     * 对常量枚举的支持
     *
     * @param tokenCacheKey
     * @return
     */
    Object get(GlobalConstant tokenCacheKey);

    /**
     * 对枚举的支持
     * @param key
     * @param value
     */
    void put(GlobalConstant key, Object value);

    /**
     * 存入
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value);

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public Object remove(String key);

    /**
     * 清理
     */
    public void clear();

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    boolean containKey(String key);
}
