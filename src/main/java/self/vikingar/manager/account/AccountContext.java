package self.vikingar.manager.account;

import self.vikingar.manager.GlobalConstant;
import self.vikingar.manager.session.SessionSupport;
import self.vikingar.manager.thread.DefaultThreadContext;

import java.util.List;

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
