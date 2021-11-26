package self.vikingar.service.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.io.IoHandler;
import self.vikingar.manager.io.pool.IoHandlerPool;
import self.vikingar.manager.record.ano.Record;
import self.vikingar.mapper.document.DocumentMapper;
import self.vikingar.model.dto.template.TemplateInsideDto;
import self.vikingar.model.vo.document.DocumentVo;
import self.vikingar.service.template.TemplateService;

import java.io.IOException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 15:50
 * @Description:
 **/
@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentMapper documentMapper;

    private final TemplateService templateService;

    private final IoHandlerPool ioHandlerPool;

    public DocumentServiceImpl(DocumentMapper documentMapper, TemplateService templateService, IoHandlerPool ioHandlerPool) {
        this.documentMapper = documentMapper;
        this.templateService = templateService;
        this.ioHandlerPool = ioHandlerPool;
    }

    @Override
    @Record(success = "用户:`username()`上传了文档:`#documentVo.getTitle()`")
    public boolean addDocument(DocumentVo documentVo) {
        //富文本  如何处理图片问题 静态解决  这里添加一个图片的管理
        TemplateInsideDto defaultTemplate = templateService.getDefaultTemplate();
        String filePath = defaultTemplate.getFilePath();
        IoHandler handler = ioHandlerPool.getHandler();
        try {
            handler.readFile(filePath);
        } catch (IOException e) {
            throw CommonException.newException("模板读取错误", e);
        }
        return false;
    }
}
