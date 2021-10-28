package self.vikingar.model.base;

import lombok.Data;
import lombok.experimental.Accessors;
import self.vikingar.manager.account.AccountContextFactory;
import self.vikingar.model.dto.account.AccountInfo;

import java.time.LocalDateTime;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/29 16:25
 * @Description:
 **/
@Data
@Accessors(chain = true)
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


    public void isInsert() {
        id = null;
        AccountInfo account = AccountContextFactory.getInstance().getAccount();
        createdId = account.id();
        createdName = account.username();
        createdTime = LocalDateTime.now();
        valid = 1;
    }

    public void isUpdate() {
        AccountInfo account = AccountContextFactory.getInstance().getAccount();
        lastModifiedId = account.id();
        lastModifiedName = account.username();
        lastModifiedTime = LocalDateTime.now();
    }

}
