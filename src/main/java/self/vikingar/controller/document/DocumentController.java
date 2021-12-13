package self.vikingar.controller.document;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.vikingar.model.base.ApiResult;
import self.vikingar.model.vo.document.DocumentVo;
import self.vikingar.service.document.DocumentService;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 15:47
 * @Description:
 **/
@RestController
@RequestMapping("document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("add")
    public ApiResult<String> addDocument(@RequestBody DocumentVo documentVo) {
        documentService.addDocument(documentVo);
        return ApiResult.success();
    }


}
