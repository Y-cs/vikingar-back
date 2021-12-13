package self.vikingar.service.document;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.record.ano.Record;
import self.vikingar.mapper.document.DocumentMapper;
import self.vikingar.model.domain.DocumentInfoDo;
import self.vikingar.model.dto.source.SourcePreUseCharIo;
import self.vikingar.model.dto.template.TemplateInsideDto;
import self.vikingar.model.enumType.DocumentStatusEnum;
import self.vikingar.model.vo.document.DocumentVo;
import self.vikingar.service.source.SourceService;
import self.vikingar.service.template.TemplateService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 15:50
 * @Description:
 **/
@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final static String HTML_SUFFIX = ".htm";
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd-HH-mm-ss");
    private final static Configuration CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    private final DocumentMapper documentMapper;

    private final TemplateService templateService;

    private final SourceService sourceService;

    public DocumentServiceImpl(DocumentMapper documentMapper, TemplateService templateService, SourceService sourceService) {
        this.documentMapper = documentMapper;
        this.templateService = templateService;
        this.sourceService = sourceService;
    }

    @Override
    @Record(success = "用户:`username()`上传了文档:`#documentVo.getTitle()`")
    public boolean addDocument(DocumentVo documentVo) {
        //存库
        DocumentInfoDo documentInfoDo = new DocumentInfoDo();
        documentInfoDo.isInsert();
        SourcePreUseCharIo document;
        switch (documentVo.getIssuingNow()) {
            case 0:
                //草稿
                documentInfoDo.setIssuingTime(Date.from(LocalDateTime.MAX.atZone(ZoneOffset.systemDefault()).toInstant()))
                        .setStatus(DocumentStatusEnum.DRAFT)
                        .setSourceId(-1L);
                break;
            case 1:
                //立刻发布
                document = createDocument(documentVo);
                documentInfoDo.setIssuingTime(new Date())
                        .setStatus(DocumentStatusEnum.PUBLISHED)
                        .setSourceId(document.getSourceId());
                break;
            case 2:
                //延迟发布
                document = createDocument(documentVo);
                documentInfoDo.setIssuingTime(documentVo.getIssuingTime())
                        .setStatus(DocumentStatusEnum.DELAYED_RELEASE)
                        .setSourceId(document.getSourceId());
                break;
            default:
                throw CommonException.newException("参数错误");
        }
        documentMapper.insert(documentInfoDo
                .setTitle(documentVo.getTitle())
                .setDocument(documentVo.getDocument()));
        return false;
    }

    private SourcePreUseCharIo createDocument(DocumentVo documentVo) {
        //获取默认模板的输入流
        TemplateInsideDto defaultTemplate = templateService.getDefaultTemplate();
        String filePath = defaultTemplate.getFilePath();
        InputStream inputStream = sourceService.readFile(filePath);
        try {
            //获取模板
            Template template = new Template(null, new InputStreamReader(inputStream), CONFIGURATION);
            //获取输出流
            SourcePreUseCharIo outputWriter = sourceService.getOutputWriter(FilePathConstant.HTML_SOURCE,
                    documentVo.getTitle() + DATE_TIME_FORMATTER.format(LocalDateTime.now()) + HTML_SUFFIX);
            try (Writer writer = outputWriter.getWriter()) {
                //生成文档
                template.process(documentVo, writer);
            }
            return outputWriter;
        } catch (IOException | TemplateException e) {
            throw CommonException.newException("模板读取错误", e);
        }
    }
}