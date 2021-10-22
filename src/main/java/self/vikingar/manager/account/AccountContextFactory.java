package self.vikingar.manager.account;

import self.vikingar.model.dto.account.AccountInfo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:38
 * @Description:
 **/
public class AccountContextFactory {

    private AccountContextFactory() {
    }

    public static <T> AccountContext<T> getInstance(Class<T> clazz) {
        return new AccountContext4Local<T>();
    }

    public static AccountContext<AccountInfo> getInstance() {
        return new AccountContext4Local<AccountInfo>();
    }


}
