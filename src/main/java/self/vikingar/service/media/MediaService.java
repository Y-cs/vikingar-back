package self.vikingar.service.media;

import self.vikingar.model.dto.media.MediaSaveOrUpdateDto;

import java.io.IOException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 16:40
 * @Description:
 **/
public interface MediaService {

    /**
     * @param dto
     * @return 图片的路径
     */
    String uploadImage(MediaSaveOrUpdateDto dto) throws IOException;


}
