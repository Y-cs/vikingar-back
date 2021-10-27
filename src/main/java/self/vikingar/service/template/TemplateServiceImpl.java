package self.vikingar.service.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.manager.io.IoSupport;
import self.vikingar.manager.io.IoSupportFactory;
import self.vikingar.mapper.source.TemplateInfoMapper;
import self.vikingar.model.domain.TemplateInfoDo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:03
 * @Description:
 **/
@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private static final String FILE_BASE = "template";
    private final IoSupport ioSupport;
    private final TemplateInfoMapper templateInfoMapper;

    public TemplateServiceImpl(TemplateInfoMapper templateInfoMapper) {
        this.templateInfoMapper = templateInfoMapper;
        ioSupport = IoSupportFactory.getInstance();
    }

    @Override
    public boolean updateTemplate(MultipartFile file, String templateName, String description, boolean isDefault) throws IOException {
        String originalFilename = file.getOriginalFilename();
        long sourceId = this.saveFile(originalFilename, file.getInputStream(), file.getSize());
        if (isDefault) {
            templateInfoMapper.clearDefault();
        }
        if (StringUtils.isBlank(templateName)) {
            templateName = Objects.requireNonNull(originalFilename).split("\\.")[0];
        }
        templateInfoMapper.insert(new TemplateInfoDo()
                .setTemplateName(templateName)
                .setDescription(description)
                .setSourceId(sourceId)
                .setDefault(isDefault)
        );
        return true;
    }

    private long saveFile(String name, InputStream inputStream, long size) throws IOException {
        ioSupport.setFolderName(FILE_BASE);
        return ioSupport.saveFile(name, inputStream, size, true);
    }
}
