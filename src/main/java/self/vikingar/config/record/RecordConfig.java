package self.vikingar.config.record;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.vikingar.manager.account.AccountContextFactory;
import self.vikingar.manager.record.config.RecordOperator;
import self.vikingar.manager.record.config.RecordRootObject;
import self.vikingar.model.dto.account.AccountInfo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 21:15
 * @Description:
 **/
@Configuration
public class RecordConfig {

    @Bean
    public RecordRootObject getSpelRootObject() {
        return () -> {
            AccountInfo account = AccountContextFactory.getInstance().getAccount();
            return account != null ? new RecordOperator().id(account.id()).name(account.username()) : new RecordOperator();
        };
    }


}
