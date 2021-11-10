package self.vikingar.manager.io;

import self.vikingar.manager.io.config.IoConfig;
import self.vikingar.manager.io.config.IoTypeEnum;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 15:24
 * @Description:
 **/
public abstract class AbstractIoHandler implements IoHandler {

    private final IoTypeEnum ioTypeEnum;

    public AbstractIoHandler(IoConfig ioConfig) {
        ioTypeEnum = ioConfig.getIoTypeEnum();
    }

    @Override
    public IoTypeEnum getFileSource() {
        return ioTypeEnum;
    }


}
