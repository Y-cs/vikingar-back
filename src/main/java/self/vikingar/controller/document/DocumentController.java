package self.vikingar.controller.document;

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
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ApiResult<String> addDocument(DocumentVo documentVo) {
        documentService.addDocument(documentVo);
        return ApiResult.success();
    }

}
