package self.vikingar.manager.account;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 17:25
 * @Description: 用户信息上下文
 **/
public interface AccountContext<T> {

    /**
     * 获取用户信息
     *
     * @return
     */
    T getAccount();

    /**
     * 存入用户信息
     *
     * @param t
     */
    void putAccount(T t);


}
