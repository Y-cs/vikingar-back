package self.vikingar.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import self.vikingar.model.base.BaseModel;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 13:29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("file_source")
@Accessors(chain = true)
public class FileSourceDo extends BaseModel {

    private String fileName;

    private String fileType;

    private String filePath;

    private long fileSize;

}
