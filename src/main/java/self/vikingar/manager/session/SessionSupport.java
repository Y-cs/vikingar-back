package self.vikingar.manager.session;

import self.vikingar.manager.GlobalConstant;
import self.vikingar.manager.thread.DefaultThreadContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 9:53
 * @Description: 会话操作支持
 **/
public class SessionSupport {

    /**
     * 获取当前线程token
     *
     * @return
     */
    public static String getToken4Session() {
        return (String) DefaultThreadContext.getInstance().get(GlobalConstant.TOKEN_CACHE_KEY);
    }

    /**
     * 存入token对应当前线程
     *
     * @param token
     */
    public static void putToken4Session(String token) {
        DefaultThreadContext.getInstance().put(GlobalConstant.TOKEN_CACHE_KEY, token);
    }

    public static void clear(){
        DefaultThreadContext.getInstance().clear();
    }
}
