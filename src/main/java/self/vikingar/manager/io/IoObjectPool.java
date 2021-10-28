package self.vikingar.manager.io;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import self.vikingar.config.exception.CommonException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 14:24
 * @Description:
 **/
public class IoObjectPool {

    private final ObjectPool<IoSupport> objectPool;

    public IoObjectPool() {
        GenericObjectPoolConfig<IoSupport> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(Runtime.getRuntime().availableProcessors() * 2);
        config.setMaxIdle(1);
        config.setMinIdle(0);
        this.objectPool = new GenericObjectPool<>(new IoSupportFactory(), config);
    }

    public IoSupport getObject() {
        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            throw new CommonException("资源繁忙");
        }
    }

    public void returnObject(IoSupport ioSupport){
        try {
            objectPool.returnObject(ioSupport);
        } catch (Exception e) {
            throw new CommonException("归还资源失败");
        }
    }

    public ObjectPool<IoSupport> getObjectPool() {
        return objectPool;
    }
}
