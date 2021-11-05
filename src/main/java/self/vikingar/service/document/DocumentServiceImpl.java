package self.vikingar.service.document;

import org.springframework.stereotype.Service;
import self.vikingar.mapper.document.DocumentMapper;
import self.vikingar.model.vo.document.DocumentVo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 15:50
 * @Description:
 **/
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Override
    public void addDocument(DocumentVo documentVo) {
        //富文本

    }
}
