package hyc.upload.dataupload.aopredislock;

import hyc.upload.dataupload.controller.AcquImpl;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LockRedis
 * @Description TODO
 * @Author DL
 * @Date 2019/7/26 10:16
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockRedis {
    String key();
    int keyExpire() default 1;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    Class <? extends AcquisitionLockFailed> lockFailedClass() default AcquImpl.class;
}
