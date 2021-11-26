package self.vikingar.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import self.vikingar.model.base.BaseModel;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 18:18
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("log_record")
public class LogRecordDo extends BaseModel {

    /**
     * businessCode       varchar(128) default ''                not null comment '业务编码',
     * result_status      tinyint(1)   default 1                 not null comment '结果,0:失败,1:成功',
     * message            varchar(512) default ''                not null comment '消息内容',
     * method             varchar(255) default ''                not null comment '方法',
     * exception          varchar(512) default ''                not null comment '错误',
     */
    private String businessCode;
    private boolean resultStatus;
    private String message;
    private String method;
    private String exception;
    private String extend;

}
