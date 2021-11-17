package self.vikingar.manager.record.parse;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:36
 * @Description:
 **/
class LogParseBySpacerTest extends LogParseBySpacer {

    @Test
    void getSpacerValue1() {
        List<String> spacerValue = super.getSpacerValue("这是一条奇怪的日志!?,参数1:`aaa`,参数2:`bbb`");
        System.out.println(spacerValue);
        assert spacerValue.size() == 2;
    }

    @Test
    void getSpacerValue2() {
        List<String> spacerValue = super.getSpacerValue("`bbb`");
        System.out.println(spacerValue);
        assert "`bbb`".equals(spacerValue.get(0));
    }

    @Test
    void getSpacerValue3() {
        List<String> spacerValue = super.getSpacerValue("`aaa`请求了,参数2:`bbb`");
        System.out.println(spacerValue);
        assert spacerValue.size() == 2;
    }

    @Test
    void getSpacerValue4() {
        List<String> spacerValue = super.getSpacerValue("参数1:`aaa`,参数2:`bbb`巴拉巴拉");
        System.out.println(spacerValue);
        assert spacerValue.size() == 2;
    }
}