package self.vikingar.model.dto.media;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import self.vikingar.model.dto.file.FileSourceDto;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 17:06
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class MediaSaveOrUpdateDto extends FileSourceDto {

}
