package self.vikingar.manager.io.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import self.vikingar.manager.io.IoHandler;
import self.vikingar.manager.io.config.IoConfig;
import self.vikingar.manager.io.impl.IoHandlerByLocalImpl;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:34
 * @Description:
 **/
@Slf4j
public class IoHandlerFactory implements PooledObjectFactory<IoHandler> {

    private final IoConfig ioConfig;

    public IoHandlerFactory(IoConfig ioConfig) {
        this.ioConfig = ioConfig;
    }

    @Override
    public void activateObject(PooledObject<IoHandler> p) throws Exception {
        log.debug("激活IO操作对象");
        p.getObject().init();
    }

    @Override
    public void destroyObject(PooledObject<IoHandler> p) throws Exception {
        log.debug("销毁IO操作对象");
        p.getObject().close();
    }

    @Override
    public PooledObject<IoHandler> makeObject() throws Exception {
        log.debug("创建IO操作对象");
        return new DefaultPooledObject<>(this.getIoSupport());
    }

    @Override
    public void passivateObject(PooledObject<IoHandler> p) throws Exception {
        log.debug("归还IO操作对象");
        p.getObject().clear();
    }

    @Override
    public boolean validateObject(PooledObject<IoHandler> p) {
        log.debug("验证IO操作对象");
        return p.getObject().validate();
    }

    public IoHandler getIoSupport() {
        return new IoHandlerByLocalImpl(ioConfig);
    }
}
