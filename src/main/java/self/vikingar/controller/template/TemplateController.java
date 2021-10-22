package self.vikingar.controller.template;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.model.base.ApiResult;
import self.vikingar.service.template.TemplateService;

import java.io.IOException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:01
 * @Description:
 **/
@RestController
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping("uploadTemplate")
    public ApiResult<String> uploadTemplate(@RequestParam("file") MultipartFile file) throws IOException {
        templateService.updateTemplate(file.getName(), file.getInputStream(), file.getSize());
        return ApiResult.success();
    }

}
