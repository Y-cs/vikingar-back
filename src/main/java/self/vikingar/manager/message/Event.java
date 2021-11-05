package self.vikingar.manager.message;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/29 9:49
 * @Description:
 **/
@Data
public class Event<T> {

    private MessageMenu event;

    private T data;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event<?> event1 = (Event<?>) o;
        return Objects.equals(event, event1.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event);
    }
}
