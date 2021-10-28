package self.vikingar.util;

import org.springframework.beans.BeanUtils;
import self.vikingar.util.function.AssemblyProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 16:26
 * @Description:
 **/
public class AssemblyFactory {

    private AssemblyFactory(){}

    public static <A, B> B assembling(A a, Class<B> clazz, AssemblyProcess<A, B> process) {
        B b = BeanUtils.instantiateClass(clazz);
        process.assembly(a, b);
        return b;
    }

    public static <A, B> B defaultAssembling(A a, Class<B> clazz) {
        return assembling(a, clazz, BeanUtils::copyProperties);
    }

    public static <A, B> List<B> listAssembling(List<A> as, Class<B> clazz) {
        List<B> bs = new ArrayList<>();
        for (A a : as) {
            bs.add(assembling(a, clazz, BeanUtils::copyProperties));
        }
        return bs;
    }

}
