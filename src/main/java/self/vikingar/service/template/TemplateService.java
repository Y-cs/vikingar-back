package self.vikingar.service.template;

import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:02
 * @Description:
 **/
public interface TemplateService {
    /**
     * 上传模板
     * @param name
     * @param inputStream
     * @param size
     * @return
     */
    boolean updateTemplate(String name, InputStream inputStream, long size);

}
