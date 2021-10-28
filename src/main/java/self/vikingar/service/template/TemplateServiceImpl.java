package self.vikingar.service.template;

import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.manager.io.IoSupport;
import self.vikingar.manager.io.IoSupportFactory;
import self.vikingar.mapper.source.TemplateInfoMapper;
import self.vikingar.model.domain.TemplateInfoDo;
import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.vo.template.TemplatePagingVo;
import self.vikingar.model.vo.template.TemplateVo;
import self.vikingar.util.AssemblyFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    public boolean insert(MultipartFile file, String templateName, String description, boolean isDefault) throws IOException {
        String originalFilename = file.getOriginalFilename();
        long sourceId = this.saveFile(file.getInputStream(), file.getSize());
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

    @Override
    public List<TemplateDto> paging(TemplatePagingVo templatePagingVo) {
        PageMethod.startPage(templatePagingVo.getPageIndex(), templatePagingVo.getPageSize());
        List<TemplateInfoDo> templateInfoDos = templateInfoMapper.selectList(null);
        return AssemblyFactory.listAssembling(templateInfoDos, TemplateDto.class);
    }

    @Override
    public boolean delete(TemplateVo templateVo) {
        return templateInfoMapper.deleteById(templateVo.getId()) > 0;
    }

    @Override
    public boolean update(TemplateVo templateVo) {
        TemplateInfoDo templateInfoDo = AssemblyFactory.defaultAssembling(templateVo, TemplateInfoDo.class);
        templateInfoDo.isUpdate();
        return templateInfoMapper.updateById(templateInfoDo) > 0;
    }

    private long saveFile(InputStream inputStream, long size) throws IOException {
        ioSupport.setFolderName(FILE_BASE);
        return ioSupport.saveFile(UUID.randomUUID().toString().replace("-", ""), inputStream, size, true);
    }
}
