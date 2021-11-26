package self.vikingar.service.media;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.vikingar.config.configuration.ApplicationConfig;
import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.config.constant.GlobalConstant;
import self.vikingar.manager.record.ano.Record;
import self.vikingar.model.domain.FileSourceDo;
import self.vikingar.model.dto.media.MediaSaveOrUpdateDto;
import self.vikingar.service.source.SourceService;
import self.vikingar.util.PathUtil;

import java.io.IOException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 17:11
 * @Description:
 **/
@Service
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final SourceService sourceService;

    private final ApplicationConfig applicationConfig;

    public MediaServiceImpl(SourceService sourceService, ApplicationConfig applicationConfig) {
        this.sourceService = sourceService;
        this.applicationConfig = applicationConfig;
    }

    @Override
    @Record(success = "上传了一张图片")
    public String uploadImage(MediaSaveOrUpdateDto dto) throws IOException {
        FileSourceDo fileSourceDo = sourceService.saveFile(FilePathConstant.MEDIA,
                dto.getOriginalFilename(),
                dto.getInputStream(),
                dto.getSize(), true);
        //这里返回的链接 需要动态处理
        return PathUtil.inspect(applicationConfig.getWebDomain()) +
                GlobalConstant.STATIC_RESOURCE_PATH.getConstant2String() +
                fileSourceDo.getSourcePath();
    }
}
