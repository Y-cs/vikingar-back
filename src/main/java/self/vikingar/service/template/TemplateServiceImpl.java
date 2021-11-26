package self.vikingar.service.template;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.record.ano.Record;
import self.vikingar.manager.record.context.RecordContextManager;
import self.vikingar.mapper.source.TemplateInfoMapper;
import self.vikingar.model.domain.FileSourceDo;
import self.vikingar.model.domain.TemplateInfoDo;
import self.vikingar.model.dto.file.FileSourceInsideDto;
import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.dto.template.TemplateInsideDto;
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
    @Transactional(rollbackFor = Exception.class)
    @Record(success = "用户:`username()``#dto.getId()==null?'上传了':'更新了'`模板:`#dto.getTemplateName()`")
    public boolean insertOrUpdate(TemplateSaveOrUpdateDto dto) throws IOException {
        boolean isSave = dto.getId() == null;
        TemplateInfoDo templateInfoDo;
        if (isSave) {
            templateInfoDo = new TemplateInfoDo();
            templateInfoDo.isInsert();
        } else {
            templateInfoDo = templateInfoMapper.selectById(dto.getId());
            if (templateInfoDo == null) {
                throw new CommonException("模板不存在");
            }
            templateInfoDo.isUpdate();
        }
        String originalFilename = Objects.requireNonNull(dto.getOriginalFilename());
        if (StringUtils.isBlank(dto.getTemplateName())) {
            StringBuilder templateNameBuilder = new StringBuilder(dto.getTemplateName());
            String[] splitName = originalFilename.split("\\.");
            for (int i = 0; i < splitName.length - 1; i++) {
                templateNameBuilder.append(".").append(splitName[i]);
            }
            templateNameBuilder.deleteCharAt(0);
            dto.setTemplateName(templateNameBuilder.toString());
        }
        AssemblyFactory.defaultTransformation(dto, templateInfoDo);
        FileSourceDo fileSourceDo = sourceService.saveFile(FilePathConstant.TEMPLATE, originalFilename, dto.getInputStream(), dto.getSize(), true);
        templateInfoDo.setSourceId(fileSourceDo.getId());
        if (dto.isDefault()) {
            templateInfoMapper.clearDefault();
        }
        return isSave ? templateInfoMapper.insert(templateInfoDo) > 0 :
                templateInfoMapper.updateById(templateInfoDo) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TemplateDto> paging(TemplatePagingVo templatePagingVo) {
        PageMethod.startPage(templatePagingVo.getPageIndex(), templatePagingVo.getPageSize());
        List<TemplateInfoDo> templateInfoDos = templateInfoMapper.selectList(null);
        return AssemblyFactory.listAssembling(templateInfoDos, TemplateDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Record(success = "用户:`username()`删除了模板:`#template.getTemplateName()`")
    public boolean delete(Long id) {
        TemplateInfoDo templateInfoDo = templateInfoMapper.selectById(id);
        RecordContextManager.INSTANCE.addParam("template",templateInfoDo);
        return templateInfoMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public TemplateInsideDto getDefaultTemplate() {
        TemplateInfoDo templateInfoDo = templateInfoMapper.selectOne(new LambdaQueryWrapper<TemplateInfoDo>().eq(TemplateInfoDo::isDefault, true));
        FileSourceInsideDto fileSource = sourceService.getFileSourceById(templateInfoDo.getSourceId());
        return AssemblyFactory.defaultTransformation(templateInfoDo, AssemblyFactory.defaultAssembling(fileSource, TemplateInsideDto.class));
    }

}
