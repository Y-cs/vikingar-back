package self.vikingar.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import self.vikingar.model.base.BaseModel;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 13:33
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("template_info")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TemplateInfoDo extends BaseModel {

    /**
     * template_name      varchar(64)  default ''                not null comment '模板名称',
     * description        varchar(512) default ''                not null comment '模板描述',
     * source_id          bigint       default 0                 not null comment '资源Id',
     * isDefault          tinyint(1)   default 0                 not null comment '默认模板,0:非默认,1:默认的',
     */

    private String templateName;
    private String description;
    private long sourceId;
    private Boolean isDefault;


}
