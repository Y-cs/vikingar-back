package self.vikingar.controller.medio;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import self.vikingar.config.exception.CommonException;
import self.vikingar.model.base.ApiResult;
import self.vikingar.model.dto.media.MediaSaveOrUpdateDto;
import self.vikingar.service.media.MediaService;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/9 15:20
 * @Description:
 **/
@RestController
@RequestMapping("media")
@Slf4j
public class MediaController {

    private static final String SEPARATOR_BY_FILE = ".";
    private static final List<String> IMG_EXTENSION = Arrays.asList("JPG", "PNG", "JPEG", "BMP", "GIF", "SVG", "ICO");

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PutMapping
    public ApiResult<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename) || !IMG_EXTENSION.contains(filename.substring(filename.indexOf(SEPARATOR_BY_FILE) + 1).toUpperCase())) {
            throw CommonException.newException("文件类型不支持");
        }
        try {
            MediaSaveOrUpdateDto mediaSaveOrUpdateDto = new MediaSaveOrUpdateDto();
            mediaSaveOrUpdateDto.setInputStream(file.getInputStream());
            mediaSaveOrUpdateDto.setSize(file.getSize());
            mediaSaveOrUpdateDto.setOriginalFilename(file.getOriginalFilename());
            return ApiResult.success(mediaService.uploadImage(mediaSaveOrUpdateDto));
        } catch (Exception e) {
            throw CommonException.newException("图片上传失败", e);
        }
    }


}
