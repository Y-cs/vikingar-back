package self.vikingar.manager.message;

import java.util.function.Consumer;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/29 9:47
 * @Description:
 **/
public interface Dispatcher {

    /**
     * 注册
     * @param messageMenu
     * @param consumer
     */
    public void register(MessageMenu messageMenu , Consumer<Event<?>> consumer);

    /**
     * 预言
     * @param event
     */
    public void prophesy(Event<?> event);

}
