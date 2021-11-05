package self.vikingar.service.document;

import self.vikingar.model.vo.document.DocumentVo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 15:50
 * @Description:
 **/
public interface DocumentService {
    /**
     * 添加文档
     * @param documentVo
     */
    void addDocument(DocumentVo documentVo);
}
