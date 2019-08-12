package hyc.upload.dataupload.controller;/**
 * @ClassName AcquImpl
 * @Description TODO
 * @Author DL
 * @Date 2019/7/26 14:12
 * @Version 1.0
 */

import hyc.upload.dataupload.aopredislock.AcquisitionLockFailed;

/**
 *@ClassName AcquImpl
 *@Description TODO
 *@Author DL
 *@Date 2019/7/26 14:12    
 *@Version 1.0
 */
public class AcquImpl extends AcquisitionLockFailed {
    @Override
    public Object lockFailed() {
        System.out.println("获取锁失败啦啦啦啦");
        return "获取锁失败啦啦啦啦";
    }
}
