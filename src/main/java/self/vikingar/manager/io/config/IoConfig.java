package self.vikingar.manager.io.config;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 15:05
 * @Description:
 **/
public abstract class IoConfig {
    private IoTypeEnum ioTypeEnum;

    public IoConfig() {
        initIoTypeEnum();
    }

    /**
     * 强制子类实现设置io类型
     */
    public abstract void initIoTypeEnum();

    public void setIoTypeEnum(IoTypeEnum ioTypeEnum) {
        this.ioTypeEnum = ioTypeEnum;
    }

    public IoTypeEnum getIoTypeEnum() {
        return ioTypeEnum;
    }
}
