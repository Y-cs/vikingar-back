package self.vikingar.manager.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:34
 * @Description:
 **/
@Slf4j
public class IoSupportFactory implements PooledObjectFactory<IoSupport> {

    @Override
    public void activateObject(PooledObject<IoSupport> p) throws Exception {
        log.info("激活IO操作对象");
        p.getObject().init();
    }

    @Override
    public void destroyObject(PooledObject<IoSupport> p) throws Exception {
        log.info("销毁IO操作对象");
        p.getObject().close();
    }

    @Override
    public PooledObject<IoSupport> makeObject() throws Exception {
        log.info("创建IO操作对象");
        return new DefaultPooledObject<>(new IoSupportByLocalImpl());
    }

    @Override
    public void passivateObject(PooledObject<IoSupport> p) throws Exception {
        log.info("归还IO操作对象");
        p.getObject().clear();
    }

    @Override
    public boolean validateObject(PooledObject<IoSupport> p) {
        return true;
    }
}
