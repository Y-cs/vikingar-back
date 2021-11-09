package self.vikingar.service.template;

import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.config.exception.CommonException;
import self.vikingar.mapper.source.TemplateInfoMapper;
import self.vikingar.model.domain.FileSourceDo;
import self.vikingar.model.domain.TemplateInfoDo;
import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.dto.template.TemplateSaveOrUpdateDto;
import self.vikingar.model.vo.template.TemplatePagingVo;
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
    public boolean insert(TemplateSaveOrUpdateDto dto) throws IOException {
        String originalFilename = Objects.requireNonNull(dto.getOriginalFilename());
        FileSourceDo fileSourceDo = sourceService.saveFile(FilePathConstant.TEMPLATE, originalFilename, dto.getInputStream(), dto.getSize(), true);
        if (dto.isDefault()) {
            templateInfoMapper.clearDefault();
        }
        if (StringUtils.isBlank(dto.getTemplateName())) {
            StringBuilder templateNameBuilder = new StringBuilder(dto.getTemplateName());
            String[] splitName = originalFilename.split("\\.");
            for (int i = 0; i < splitName.length - 1; i++) {
                templateNameBuilder.append(".").append(splitName[i]);
            }
            templateNameBuilder.deleteCharAt(0);
            dto.setTemplateName(templateNameBuilder.toString());
        }
        return templateInfoMapper.insert(new TemplateInfoDo()
                .setTemplateName(dto.getTemplateName())
                .setDescription(dto.getDescription())
                .setSourceId(fileSourceDo.getId())
                .setDefault(dto.isDefault())
        ) > 0;
    }

    @Override
    public List<TemplateDto> paging(TemplatePagingVo templatePagingVo) {
        PageMethod.startPage(templatePagingVo.getPageIndex(), templatePagingVo.getPageSize());
        List<TemplateInfoDo> templateInfoDos = templateInfoMapper.selectList(null);
        return AssemblyFactory.listAssembling(templateInfoDos, TemplateDto.class);
    }

    @Override
    public boolean delete(Long id) {
        return templateInfoMapper.deleteById(id) > 0;
    }

    @Override
    public boolean update(TemplateSaveOrUpdateDto dto) throws IOException {
        TemplateInfoDo templateInfoDo = templateInfoMapper.selectById(dto.getId());
        if (templateInfoDo == null) {
            throw new CommonException("模板不存在");
        }
        String originalFilename = Objects.requireNonNull(dto.getOriginalFilename());
        FileSourceDo fileSourceDo = sourceService.saveFile(FilePathConstant.TEMPLATE, originalFilename, dto.getInputStream(), dto.getSize(), true);
        if (dto.isDefault()) {
            templateInfoMapper.clearDefault();
        }
        if (StringUtils.isBlank(dto.getTemplateName())) {
            dto.setTemplateName(originalFilename.split("\\.")[0]);
        }
        templateInfoDo.isUpdate();
        return templateInfoMapper.updateById(templateInfoDo
                .setTemplateName(dto.getTemplateName())
                .setSourceId(fileSourceDo.getId())
                .setDescription(dto.getDescription())
                .setDefault(dto.isDefault())) > 0;
    }

}
