package self.vikingar.manager.io.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.Reader;
import java.io.Writer;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/29 15:40
 * @Description:
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PreUseCharIo {

    private Writer writer;

    private Reader reader;

    private File file;

}
