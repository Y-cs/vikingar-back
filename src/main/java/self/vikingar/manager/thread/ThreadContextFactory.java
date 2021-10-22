package self.vikingar.manager.thread;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:36
 * @Description:
 **/
public class ThreadContextFactory {

    private ThreadContextFactory() {
    }

    public static ThreadContext getInstance() {
        return DefaultThreadContext.getInstance();
    }

}
