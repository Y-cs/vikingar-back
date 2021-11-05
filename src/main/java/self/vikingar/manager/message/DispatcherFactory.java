package self.vikingar.manager.message;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/29 9:58
 * @Description:
 **/
public class DispatcherFactory {

    private static final Dispatcher4Local DISPATCHER = new Dispatcher4Local();

    private DispatcherFactory() {
    }

    public static Dispatcher getInstance() {
        return DISPATCHER;
    }

}
