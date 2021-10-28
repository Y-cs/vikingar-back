package self.vikingar.service.source;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 13:57
 * @Description:
 **/
public interface SourceService {
    long saveFile(String folder, String fileName, InputStream inputStream, long size, boolean cover) throws IOException;
}
