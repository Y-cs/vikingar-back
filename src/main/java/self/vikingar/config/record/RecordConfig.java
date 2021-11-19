package self.vikingar.config.record;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.vikingar.manager.account.AccountContextFactory;
import self.vikingar.manager.record.config.SpelRootObject;
import self.vikingar.model.dto.account.AccountInfo;

import java.util.Optional;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 21:15
 * @Description:
 **/
@Configuration
public class RecordConfig {

    @Bean
    public SpelRootObject getSpelRootObject() {
        return () -> {
            AccountInfo account = AccountContextFactory.getInstance().getAccount();
            return account != null ? Optional.ofNullable(account.id()).orElse(-1L) : -1;
        };
    }


}
