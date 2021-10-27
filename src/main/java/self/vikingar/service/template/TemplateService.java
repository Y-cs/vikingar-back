package self.vikingar.service.template;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:02
 * @Description:
 **/
public interface TemplateService {

    /**
     * 上传模板
     * @param file
     * @param templateName
     * @param description
     * @param isDefault
     * @return
     * @throws IOException
     */
    boolean updateTemplate(MultipartFile file, String templateName, String description, boolean isDefault) throws IOException;

}
