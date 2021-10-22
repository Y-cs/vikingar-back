package self.vikingar.manager.io;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:34
 * @Description:
 **/
public class IoSupportFactory {

    private IoSupportFactory() {
    }

    public static IoSupport getInstance() {
        return new IoSupportByLocalImpl();
    }

}
