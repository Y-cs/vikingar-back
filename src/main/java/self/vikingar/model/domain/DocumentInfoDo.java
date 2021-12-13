package self.vikingar.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import self.vikingar.model.base.BaseModel;
import self.vikingar.model.enumType.DocumentStatusEnum;

import java.util.Date;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/2 15:47
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("document_info")
@Accessors(chain = true)
public class DocumentInfoDo extends BaseModel {
    /**
     * title              varchar(128) default ''                not null comment '标题',
     * document           text                                   not null comment '文档内容',
     * issuing_time       datetime     default current_timestamp not null comment '发布时间',
     * status             tinyint      default 0                 not null comment '状态:0-草稿,1-已发布,2-定时发布',
     */
    private String title;
    private String document;
    private Long sourceId;
    private Date issuingTime;
    private DocumentStatusEnum status;
}
