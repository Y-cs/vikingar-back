package self.vikingar.service.account;

import self.vikingar.model.dto.account.AuthDto;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 16:14
 * @Description:
 **/
public interface AccountService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    AuthDto login(String username, String password);
}
