package self.vikingar.mapper.source;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import self.vikingar.model.domain.TemplateInfoDo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 13:35
 * @Description:
 **/
@Repository
public interface TemplateInfoMapper extends BaseMapper<TemplateInfoDo> {
    /**
     * 清楚默认
     */
    void clearDefault();
}
