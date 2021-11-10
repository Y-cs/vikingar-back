package self.vikingar.service.source;

import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.model.domain.FileSourceDo;
import self.vikingar.model.dto.file.FileSourceInsideDto;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 13:57
 * @Description:
 **/
public interface SourceService {
    /**
     * 保存文件
     *
     * @param folder
     * @param fileName
     * @param inputStream
     * @param size
     * @param cover       是否覆盖
     * @return
     * @throws IOException
     */
    FileSourceDo saveFile(FilePathConstant folder, String fileName, InputStream inputStream, long size, boolean cover) throws IOException;

    /**
     * 获取资源
     * @param sourceId
     * @return
     */
    FileSourceInsideDto getFileSourceById(long sourceId);
}
