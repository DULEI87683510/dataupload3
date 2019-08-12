package hyc.upload.dataupload.controller;


import annotation.MyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import configure.InterceptorConfig;

import hyc.upload.dataupload.ThreadPoolExecutor.MyRunable;
import hyc.upload.dataupload.ThreadPoolExecutor.ThreadPool;
import hyc.upload.dataupload.aopredislock.AcquisitionLockFailed;
import hyc.upload.dataupload.aopredislock.LockRedis;
import hyc.upload.dataupload.rabbitmq.RabbitConfig;
import hyc.upload.dataupload.rabbitmq.producer.Producer;
import hyc.upload.dataupload.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName Test
 *@Description TODO
 *@Author DL
 *@Date 2019/6/17 0:28    
 *@Version 1.0
 */
@RestController
@RequestMapping("/test")
@MyResponse
public class Test implements ApplicationContextAware {

    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;
    private ApplicationContext applicationContext;
    private static final Logger log = LoggerFactory.getLogger(Test.class);
    @GetMapping("/get")
    @LockRedis(key = "xxxx", keyExpire = 2, timeUnit = TimeUnit.DAYS)
    public List<String> test() {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
         /*   try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        return list;


    }

    @GetMapping("/amqp/{param}")
    public String testAmqp(@PathVariable("param") String param) {
        producer.send(param);
        return param;

    }

    @GetMapping("/log/{content}")
    public String testLogAmqp(@PathVariable("content") String content) throws JsonProcessingException {
        producer.sendRabbit(content);
        return content;

    }

    @GetMapping("/order/{content}")
    public String testorderAmqp(@PathVariable("content") String content) throws JsonProcessingException {
        initThread();
        return content;

    }

    @GetMapping("/thread/pool")
    public String testThreadPool() {
        ThreadPoolExecutor executor = ThreadPool.getThreadPool();
        for (int i = 0; i < 20; i++) {
            MyRunable runable = new MyRunable(i);
            executor.execute(runable);
            log.info("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完别的任务数目：" + executor.getCompletedTaskCount());
        }

    return "success";
    }
    public void initThread(){
        System.out.println("初始化线程数");
        CountDownLatch countDownLatch=new CountDownLatch(1);
        for (int i=0;i<1000;i++){

            new Thread(new RunThread(countDownLatch)).start();
        }
        //启动多个线程
        countDownLatch.countDown();
    }
     private int num;
    //模拟线程高并发
    public class RunThread implements Runnable{
        private final CountDownLatch countDownLatch;
        public RunThread(CountDownLatch countDownLatch){
            this.countDownLatch=countDownLatch;

        }
        @Override
        public void run() {
            try {
                //线程等待
                countDownLatch.await();
                num+=1;
                producer.sendOrder("message"+num);
                System.out.println("message"+num+"发送成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
