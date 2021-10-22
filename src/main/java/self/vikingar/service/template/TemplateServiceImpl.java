package self.vikingar.service.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.vikingar.manager.io.IoSupport;
import self.vikingar.manager.io.IoSupportFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:03
 * @Description:
 **/
@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private final IoSupport ioSupport;

    public TemplateServiceImpl() {
        ioSupport = IoSupportFactory.getInstance();
    }

    @Override
    public boolean updateTemplate(String name, InputStream inputStream, long size) {
        try {
            return ioSupport.saveFile(name, inputStream, size);
        } catch (IOException e) {
            log.error("保存文件发生错误:{0}", e);
        }
        return false;
    }
}
