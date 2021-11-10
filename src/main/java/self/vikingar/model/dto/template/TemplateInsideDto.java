package self.vikingar.model.dto.template;

import lombok.Data;
import lombok.EqualsAndHashCode;
import self.vikingar.model.dto.file.FileSourceInsideDto;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 13:50
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateInsideDto extends FileSourceInsideDto {

    private Long id;
    private String templateName;
    private String description;
    private long sourceId;
    private boolean isDefault;

}
