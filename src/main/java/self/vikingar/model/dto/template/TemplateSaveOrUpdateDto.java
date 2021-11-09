package self.vikingar.model.dto.template;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import self.vikingar.model.dto.file.FileSourceDto;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 16:55
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class TemplateSaveOrUpdateDto extends FileSourceDto {

    private Long id;

    private String templateName;

    private String description;

    private boolean isDefault;

}
