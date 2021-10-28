package self.vikingar.controller.template;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.model.base.ApiResult;
import self.vikingar.model.base.PageResult;
import self.vikingar.model.dto.template.TemplateDto;
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

    @PutMapping
    public ApiResult<?> insert(@RequestParam("file") MultipartFile file,
                                    @RequestParam(value = "templateName", defaultValue = "") String templateName,
                                    @RequestParam(value = "description", defaultValue = "") String description,
                                    @RequestParam(value = "isDefault", defaultValue = "false") boolean isDefault
    ) throws IOException {
        templateService.insert(file, templateName, description, isDefault);
        return ApiResult.success();
    }

    @GetMapping
    public PageResult<TemplateDto> paging(@RequestBody TemplatePagingVo templatePagingVo) {
        List<TemplateDto> date = templateService.paging(templatePagingVo);
        return PageResult.paging(date);
    }

    @DeleteMapping
    public ApiResult<?> delete(@RequestBody TemplateVo templateVo) {
        return templateService.delete(templateVo) ? ApiResult.success() : ApiResult.fail();
    }

    @PostMapping
    public ApiResult<?> update(@RequestBody TemplateVo templateVo) {
        return templateService.update(templateVo) ? ApiResult.success() : ApiResult.fail();
    }

}
