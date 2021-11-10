package self.vikingar.model.dto.file;

import lombok.Data;
import self.vikingar.manager.io.config.IoTypeEnum;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 13:54
 * @Description:
 **/
@Data
public class FileSourceInsideDto {

    private String fileName;

    private String fileType;

    private IoTypeEnum fileSource;

    private String sourcePath;

    private String filePath;

    private long fileSize;

}
