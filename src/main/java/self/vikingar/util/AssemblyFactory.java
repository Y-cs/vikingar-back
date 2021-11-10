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

    private AssemblyFactory() {
    }

    public static <A, B> B transformation(A source, B target, AssemblyProcess<A, B> process) {
        process.assembly(source, target);
        return target;
    }

    public static <A, B> B defaultTransformation(A source, B target) {
        return transformation(source,target,BeanUtils::copyProperties);
    }

    public static <A, B> B assembling(A source, Class<B> targetClazz, AssemblyProcess<A, B> process) {
        B target = BeanUtils.instantiateClass(targetClazz);
        process.assembly(source, target);
        return target;
    }

    public static <A, B> B defaultAssembling(A source, Class<B> targetClazz) {
        return assembling(source, targetClazz, BeanUtils::copyProperties);
    }

    public static <A, B> List<B> listAssembling(List<A> sources, Class<B> targetClazz) {
        List<B> targets = new ArrayList<>();
        for (A source : sources) {
            targets.add(assembling(source, targetClazz, BeanUtils::copyProperties));
        }
        return targets;
    }

}
