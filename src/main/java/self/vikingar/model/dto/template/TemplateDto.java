package self.vikingar.model.dto.template;

import lombok.Data;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 15:56
 * @Description:
 **/
@Data
public class TemplateDto {
    private Long id;
    private String templateName;
    private String description;
    private long sourceId;
    private boolean isDefault;
}
