package self.vikingar.model.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;
import self.vikingar.manager.session.TokenCreator;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 16:19
 * @Description:
 **/
@Data
@Accessors(chain = true)
public class AuthDto {

    private String token;

}
