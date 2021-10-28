package self.vikingar.manager.account;

import org.junit.jupiter.api.Test;
import self.vikingar.manager.session.SessionSupport;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 11:29
 * @Description:
 **/
class AccountContext4LocalTest {

    private final AccountContext4Local<Object> accountContext = new AccountContext4Local<>();

    @Test
    void checkSession() {
        String token = "token";
        SessionSupport.putToken4Session(token);
        String token4Session = SessionSupport.getToken4Session();
        assert token4Session.equals(token);
    }

    @Test
    void checkAccount() {
        String token = "token";
        SessionSupport.putToken4Session(token);
        Object account = new Object();
        accountContext.putAccount(account);
        Object account1 = accountContext.getAccount();
        assert account1.equals(account);
    }

}