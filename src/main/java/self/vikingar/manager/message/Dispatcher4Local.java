package self.vikingar.manager.message;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/29 9:58
 * @Description:
 **/
public class Dispatcher4Local implements Dispatcher {

    private final static EnumMap<MessageMenu, List<Consumer<Event<?>>>> CACHE = new EnumMap<>(MessageMenu.class);

    private final static ReadWriteLock LOCK = new ReentrantReadWriteLock();

    @Override
    public void register(MessageMenu messageMenu, Consumer<Event<?>> consumer) {
        Lock lock = LOCK.writeLock();
        lock.lock();
        try {
            List<Consumer<Event<?>>> list = Optional.ofNullable(CACHE.get(messageMenu)).orElse(new ArrayList<>());
            list.add(consumer);
            CACHE.put(messageMenu, list);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void prophesy(Event<?> event) {
        Lock lock = LOCK.readLock();
        lock.lock();
        try {
            List<Consumer<Event<?>>> consumers = CACHE.get(event.getEvent());
            for (Consumer<Event<?>> consumer : consumers) {
                consumer.accept(event);
            }
        } finally {
            lock.unlock();
        }
    }


}