package self.vikingar.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import self.vikingar.model.base.BaseModel;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 16:22
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("account_info")
public class AccountDo extends BaseModel {

    /**
     * username           varchar(30) default ''                not null comment '用户名',
     * `password`         varchar(50) default ''                not null comment '密码',
     * deactivate         tinyint(1)  default 0                 not null comment '是否停用,0:启用,1:停用',
     */

    private String username;
    private String password;
    private Integer deactivate;

}
