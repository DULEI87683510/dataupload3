package hyc.upload.dataupload.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPool
 * @Description TODO
 * @Author DL
 * @Date 2019/8/9 15:38
 * @Version 1.0
 */
public class ThreadPool {
    private static  ThreadPoolExecutor executor;

    public static synchronized ThreadPoolExecutor getThreadPool() {
        if (null == executor) {
            executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), new ThreadFactorys(), new Reject());
        }
        return executor;
    }

}