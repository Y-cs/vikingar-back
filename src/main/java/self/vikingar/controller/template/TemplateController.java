package self.vikingar.controller.template;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.model.base.ApiResult;
import self.vikingar.model.base.PageResult;
import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.dto.template.TemplateSaveOrUpdateDto;
import self.vikingar.model.vo.template.TemplatePagingVo;
import self.vikingar.model.vo.template.TemplateVo;
import self.vikingar.service.template.TemplateService;

import java.io.IOException;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:01
 * @Description:
 **/
@RestController
@RequestMapping("template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping("save")
    public ApiResult<String> insert(@RequestParam("file") MultipartFile file,
                                    @RequestParam(value = "templateName", defaultValue = "") String templateName,
                                    @RequestParam(value = "description", defaultValue = "") String description,
                                    @RequestParam(value = "isDefault", defaultValue = "false") boolean isDefault
    ) throws IOException {
        TemplateSaveOrUpdateDto fileSourceDto = new TemplateSaveOrUpdateDto();
        fileSourceDto.setTemplateName(templateName);
        fileSourceDto.setDescription(description);
        fileSourceDto.setDefault(isDefault);
        fileSourceDto.setOriginalFilename(file.getOriginalFilename());
        fileSourceDto.setInputStream(file.getInputStream());
        fileSourceDto.setSize(file.getSize());
        templateService.insertOrUpdate(fileSourceDto);
        return ApiResult.success();
    }

    @PostMapping("paging")
    public PageResult<TemplateDto> paging(@RequestBody TemplatePagingVo templatePagingVo) {
        List<TemplateDto> date = templateService.paging(templatePagingVo);
        return PageResult.paging(date);
    }

    @PostMapping("delete")
    public ApiResult<String> delete(@RequestBody TemplateVo templateVo) {
        return templateService.delete(templateVo.getId()) ? ApiResult.success() : ApiResult.fail();
    }

    @PostMapping("update")
    public ApiResult<String> update(@RequestParam("file") MultipartFile file,
                                    @RequestParam("id") Long id,
                                    @RequestParam(value = "templateName", defaultValue = "") String templateName,
                                    @RequestParam(value = "description", defaultValue = "") String description,
                                    @RequestParam(value = "isDefault", defaultValue = "false") boolean isDefault) throws IOException {
        TemplateSaveOrUpdateDto fileSourceDto = new TemplateSaveOrUpdateDto();
        fileSourceDto.setId(id);
        fileSourceDto.setTemplateName(templateName);
        fileSourceDto.setDescription(description);
        fileSourceDto.setDefault(isDefault);
        fileSourceDto.setOriginalFilename(file.getOriginalFilename());
        fileSourceDto.setInputStream(file.getInputStream());
        fileSourceDto.setSize(file.getSize());
        return templateService.insertOrUpdate(fileSourceDto) ? ApiResult.success() : ApiResult.fail();
    }

}
