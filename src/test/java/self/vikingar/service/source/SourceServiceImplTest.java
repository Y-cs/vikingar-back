package self.vikingar.service.source;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 16:40
 * @Description:
 **/
class SourceServiceImplTest {

    @Test
    void getName() throws IOException {

        SourceServiceImpl sourceService = new SourceServiceImpl(null);
        String s = new String("askjdfjalksdjfklasjfklandsklvnzxmcvn,mdfnglqenrglajgldjgklajlkgjqeiopgjqeoigeq");
        String aaa = sourceService.getName("aaa.txt", s.getBytes(StandardCharsets.UTF_8), 1000);
        System.out.println(aaa);
        String aaa1 = sourceService.getName("aaa.txt", s.getBytes(StandardCharsets.UTF_8), 1000);
        System.out.println(aaa1);

    }
}