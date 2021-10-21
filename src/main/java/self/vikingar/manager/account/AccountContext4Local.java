package self.vikingar.manager.account;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.springframework.stereotype.Component;
import self.vikingar.manager.session.SessionSupport;

import java.util.concurrent.TimeUnit;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 17:26
 * @Description: 本地存储用户信息上下文方案
 **/
@Component
public class AccountContext4Local<T> implements AccountContext<T> {

    private final Cache<String, T> CACHE = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.HOURS).build();

    @Override
    public T getAccount() {
        return CACHE.getIfPresent(SessionSupport.getToken4Session());
    }

    @Override
    public void putAccount(T o) {
        CACHE.put(SessionSupport.getToken4Session(), o);
    }

}
