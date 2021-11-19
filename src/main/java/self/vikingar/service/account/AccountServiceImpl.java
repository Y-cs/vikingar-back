package self.vikingar.service.account;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.account.AccountContext;
import self.vikingar.manager.account.AccountContextFactory;
import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.context.RecordContext;
import self.vikingar.manager.session.SessionSupport;
import self.vikingar.manager.session.TokenCreator;
import self.vikingar.mapper.account.AccountMapper;
import self.vikingar.model.domain.AccountDo;
import self.vikingar.model.dto.account.AccountInfo;
import self.vikingar.model.dto.account.AuthDto;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 16:15
 * @Description:
 **/
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    private final AccountContext<AccountInfo> accountContext;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
        this.accountContext = AccountContextFactory.getInstance();
    }

    @LogRecord(success = "用户:`#username`登陆成功!", fail = "用户:`#username`登陆失败!", preParsing = false)
    @Override
    public AuthDto login(String username, String password) {
        AccountDo accountDo = accountMapper.selectOne(new LambdaQueryWrapper<AccountDo>()
                .eq(AccountDo::getValid, 1)
                .eq(AccountDo::getUsername, username));
        if (accountDo == null) {
            throw CommonException.newException("账户不存在");
        }
        if (StringUtils.isEmpty(password) || !password.equals(accountDo.getPassword())) {
            throw CommonException.newException("密码错误");
        }
        if (accountDo.getDeactivate() == null || accountDo.getDeactivate() == 1) {
            throw CommonException.newException("账户停用");
        }
        String token = TokenCreator.create();
        SessionSupport.putToken4Session(token);
        accountContext.putAccount(new AccountInfo()
                .id(accountDo.getId())
                .deactivate(accountDo.getDeactivate())
                .username(accountDo.getUsername())
                .password(accountDo.getPassword())
        );
        RecordContext.INSTANCE.addParam("ofContent", accountDo);
        return new AuthDto().setToken(token);
    }


}
