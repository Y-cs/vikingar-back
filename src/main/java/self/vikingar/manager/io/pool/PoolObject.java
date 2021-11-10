package self.vikingar.manager.io.pool;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 15:38
 * @Description:
 **/
public interface PoolObject {

    /**
     * 初始化
     */
    default void init() {
    }

    ;

    /**
     * 关闭
     */
    default void close() {
    }

    ;

    /**
     * 清理 杂质
     */
    default void clear() {
    }

    ;

    /**
     * 验证
     *
     * @return
     */
    default boolean validate() {
        return true;
    }

    ;

}
