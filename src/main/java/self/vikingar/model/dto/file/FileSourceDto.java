package self.vikingar.model.dto.file;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 17:08
 * @Description:
 **/
@Data
@Accessors(chain = true)
public class FileSourceDto {

    private String originalFilename;

    private InputStream inputStream;

    private long size;

}
