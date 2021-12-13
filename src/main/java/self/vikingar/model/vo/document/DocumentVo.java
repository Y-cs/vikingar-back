package self.vikingar.model.vo.document;

import lombok.Data;

import java.util.Date;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 17:01
 * @Description:
 **/
@Data
public class DocumentVo {

    private String title;

    private String document;

    /**
     * 0-草稿,1-立即发布,2-定时发布
     */
    private Integer issuingNow;

    private Date issuingTime;

}
