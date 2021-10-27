package self.vikingar.manager.account;

import self.vikingar.model.dto.account.AccountInfo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:38
 * @Description:
 **/
public class AccountContextFactory {

    private static final AccountContext<AccountInfo> ACCOUNT_CONTEXT = new AccountContext4Local<>();

    private AccountContextFactory() {
    }

    public static AccountContext<AccountInfo> getInstance() {
        return ACCOUNT_CONTEXT;
    }


}
