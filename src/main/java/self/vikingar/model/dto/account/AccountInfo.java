package self.vikingar.model.dto.account;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 14:43
 * @Description:
 **/
@Data
@Accessors(fluent = true)
public class AccountInfo {

    private Long id;
    private String username;
    private String password;
    private Integer deactivate;


}
