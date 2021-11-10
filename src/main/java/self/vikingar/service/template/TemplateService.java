package self.vikingar.service.template;

import self.vikingar.model.dto.template.TemplateDto;
import self.vikingar.model.dto.template.TemplateInsideDto;
import self.vikingar.model.dto.template.TemplateSaveOrUpdateDto;
import self.vikingar.model.vo.template.TemplatePagingVo;

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
     * @param dto
     * @return
     * @throws IOException
     */
    boolean insertOrUpdate(TemplateSaveOrUpdateDto dto) throws IOException;

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
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 获取默认模板
     * @return
     */
    TemplateInsideDto getDefaultTemplate();
}
