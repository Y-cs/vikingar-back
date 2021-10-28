package self.vikingar.service.template;

import org.springframework.web.multipart.MultipartFile;
import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.vo.template.TemplatePagingVo;
import self.vikingar.model.vo.template.TemplateVo;

import java.io.IOException;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:02
 * @Description:
 **/
public interface TemplateService {

    /**
     * 上传模板
     *
     * @param file
     * @param templateName
     * @param description
     * @param isDefault
     * @return
     * @throws IOException
     */
    boolean insert(MultipartFile file, String templateName, String description, boolean isDefault) throws IOException;

    /**
     * 分页
     *
     * @param templatePagingVo
     * @return
     */
    List<TemplateDto> paging(TemplatePagingVo templatePagingVo);

    /**
     * 删除
     *
     * @param templateVo
     * @return
     */
    boolean delete(TemplateVo templateVo);

    /**
     * 更新
     *
     * @param id
     * @param file
     * @param templateName
     * @param description
     * @param isDefault
     * @return
     * @throws IOException
     */
    boolean update(Long id, MultipartFile file, String templateName, String description, boolean isDefault) throws IOException;
}
