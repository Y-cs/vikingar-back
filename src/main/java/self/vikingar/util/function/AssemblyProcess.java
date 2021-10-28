package self.vikingar.util.function;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 16:27
 * @Description:
 **/
@FunctionalInterface
public interface AssemblyProcess<A, B> {

    /**
     * 由A->B
     *
     * @param a
     * @param b
     * @return
     */
    void assembly(A a, B b);

}
