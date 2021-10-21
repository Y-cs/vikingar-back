package self.vikingar.model.base;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/29 16:25
 * @Description:
 **/
@Data
public class BaseModel {

    /**
     * db comment ->
     */
    private Long id;

    /**
     * db comment -> 创建人id
     */
    private Long createdId;

    /**
     * db comment -> 创建人
     */
    private String createdName;

    /**
     * db comment -> 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * db comment -> 最后修改人id
     */
    private Long lastModifiedId;

    /**
     * db comment -> 最后修改人
     */
    private String lastModifiedName;

    /**
     * db comment -> 最后修改时间
     */
    private LocalDateTime lastModifiedTime;

    /**
     * db comment -> 是否有效(0无效  1有效)
     */
    private Integer valid;

}
