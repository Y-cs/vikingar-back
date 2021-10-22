package self.vikingar.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 14:15
 * @Description:
 **/
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(3, 20, 5,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
                new ThreadFactoryBuilder().setNameFormat("Spring-Async-%d").build());
    }

    @Override
    public Executor getAsyncExecutor() {
        return THREAD_POOL_EXECUTOR;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }

    public static void sync(Runnable runnable) {
        THREAD_POOL_EXECUTOR.execute(runnable);
    }

}
