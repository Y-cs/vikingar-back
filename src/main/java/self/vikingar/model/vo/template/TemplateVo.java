package self.vikingar.model.vo.template;

import lombok.Data;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 17:05
 * @Description:
 **/
@Data
public class TemplateVo {

    private Long id;
    private String templateName;
    private String description;
    private long sourceId;
    private boolean isDefault;

}
