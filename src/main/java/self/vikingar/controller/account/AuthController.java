package self.vikingar.controller.account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.vikingar.ano.NoLoginRequired;
import self.vikingar.model.base.ApiResult;
import self.vikingar.model.dto.account.AuthDto;
import self.vikingar.model.vo.account.AuthVo;
import self.vikingar.service.account.AccountService;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 16:14
 * @Description:
 **/
@RestController
@RequestMapping("account")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    @NoLoginRequired
    public ApiResult<AuthDto> login(@RequestBody AuthVo authVo) {
        return ApiResult.success(accountService.login(authVo.getUsername(), authVo.getPassword()));
    }


}
