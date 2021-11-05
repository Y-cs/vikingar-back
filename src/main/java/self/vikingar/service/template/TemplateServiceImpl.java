package self.vikingar.service.template;

import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.config.exception.CommonException;
import self.vikingar.mapper.source.TemplateInfoMapper;
import self.vikingar.model.domain.TemplateInfoDo;
import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.vo.template.TemplatePagingVo;
import self.vikingar.model.vo.template.TemplateVo;
import self.vikingar.service.source.SourceService;
import self.vikingar.util.AssemblyFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:03
 * @Description:
 **/
@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private final TemplateInfoMapper templateInfoMapper;
    private final SourceService sourceService;

    public TemplateServiceImpl(TemplateInfoMapper templateInfoMapper, SourceService sourceService) {
        this.templateInfoMapper = templateInfoMapper;
        this.sourceService = sourceService;
    }

    @Override
    public boolean insert(MultipartFile file, String templateName, String description, boolean isDefault) throws IOException {
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        long sourceId = sourceService.saveFile(FilePathConstant.TEMPLATE.getPath(), originalFilename, file.getInputStream(), file.getSize(), true);
        if (isDefault) {
            templateInfoMapper.clearDefault();
        }
        if (StringUtils.isBlank(templateName)) {
            StringBuilder templateNameBuilder = new StringBuilder(templateName);
            String[] splitName = originalFilename.split("\\.");
            for (int i = 0; i < splitName.length - 1; i++) {
                templateNameBuilder.append(".").append(splitName[i]);
            }
            templateNameBuilder.deleteCharAt(0);
            templateName = templateNameBuilder.toString();
        }
        return templateInfoMapper.insert(new TemplateInfoDo()
                .setTemplateName(templateName)
                .setDescription(description)
                .setSourceId(sourceId)
                .setDefault(isDefault)
        ) > 0;
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
    public boolean update(Long id, MultipartFile file, String templateName, String description, boolean isDefault) throws IOException {
        TemplateInfoDo templateInfoDo = templateInfoMapper.selectById(id);
        if (templateInfoDo == null) {
            throw new CommonException("模板不存在");
        }
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        long sourceId = sourceService.saveFile(FilePathConstant.TEMPLATE.getPath(), originalFilename, file.getInputStream(), file.getSize(), true);
        if (isDefault) {
            templateInfoMapper.clearDefault();
        }
        if (StringUtils.isBlank(templateName)) {
            templateName = originalFilename.split("\\.")[0];
        }
        templateInfoDo.isUpdate();
        return templateInfoMapper.updateById(templateInfoDo
                .setTemplateName(templateName)
                .setSourceId(sourceId)
                .setDescription(description)
                .setDefault(isDefault)) > 0;
    }

}
