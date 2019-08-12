package hyc.upload.dataupload.aopredislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *@ClassName RedisLock
 *@Description TODO
 *@Author DL
 *@Date 2019/7/29 10:00    
 *@Version 1.0
 */
@Component
public class RedisLock {

    @Autowired
    private ValueOperations valueOperations;


    public boolean lock(String key,int time,TimeUnit timeUnit){
        String value="redisVal";
        boolean isSuccess= valueOperations.setIfAbsent(key,value);
        if (isSuccess){
            valueOperations.getOperations().expire(key,time,timeUnit);
            return true;
        }
      return false;

    }


    public boolean unlock(String key){
        //valueOperations.getOperations().getExpire()
        boolean isSuccess=false;
        try{
        isSuccess=  valueOperations.getOperations().delete(key);
        }catch (Exception e){
            System.out.println("解锁失败");
            e.printStackTrace();
        }
        return isSuccess;
    }


}
