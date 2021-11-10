package self.vikingar.manager.io.pool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.io.IoHandler;
import self.vikingar.manager.io.config.IoConfig;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 14:24
 * @Description:
 **/
public class IoHandlerPool {

    private final ObjectPool<IoHandler> objectPool;

    public IoHandlerPool(IoConfig ioConfig) {
        GenericObjectPoolConfig<IoHandler> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(Runtime.getRuntime().availableProcessors() * 2);
        config.setMaxIdle(1);
        config.setMinIdle(0);
        this.objectPool = new GenericObjectPool<>(new IoHandlerFactory(ioConfig), config);
    }

    public IoHandler getHandler() {
        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            throw new CommonException("资源繁忙");
        }
    }

    public void returnHandler(IoHandler ioSupport){
        try {
            objectPool.returnObject(ioSupport);
        } catch (Exception e) {
            throw new CommonException("归还资源失败");
        }
    }

    public ObjectPool<IoHandler> getObjectPool() {
        return objectPool;
    }
}
