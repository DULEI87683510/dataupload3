package hyc.upload.dataupload.aopredislock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.ClassSignature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName RedisLockAspect
 *@Description TODO
 *@Author DL
 *@Date 2019/7/26 11:04    
 *@Version 1.0
 */
@Component
@Aspect
public class RedisLockAspect {
    @Autowired
    private RedisLock redisLock;

    @Around("@annotation(LockRedis)")
    public Object aroundLockMethod(ProceedingJoinPoint pjp) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
        Signature signature = pjp.getSignature();
        //获取参数名
        MethodSignature methodSignature = (MethodSignature) signature;
        LockRedis lockRedis = methodSignature.getMethod().getAnnotation(LockRedis.class);

       String key= lockRedis.key();

              int exprie=lockRedis.keyExpire();
        TimeUnit timeUnit=lockRedis.timeUnit();
        System.out.println("key:"+key+"exprie:"+exprie+"timeunit:"+timeUnit);
        Object result=null;
        if (redisLock.lock(key,exprie,timeUnit)){
            System.out.println("加锁成功");

        //加锁成功
            try {
            result=   pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }    finally {
                //解锁
                redisLock.unlock(key);
                System.out.println("解锁");
            }

        }else{
            Class clss=lockRedis.lockFailedClass();
            Method    method=  clss.getDeclaredMethod("lockFailed");
            boolean isAbstract = Modifier.isAbstract(clss.getModifiers());
            if (!isAbstract){
                method.invoke(clss.newInstance());
            }else{
                System.out.println("获取锁失败");
            }

            //acquisitionLockFailed.lockFailed();
        }
        return result;
    }
}
