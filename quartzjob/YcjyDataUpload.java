package hyc.upload.dataupload.quartzjob;

import hyc.upload.dataupload.service.DataReportService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//远程教育的数据上传
public class YcjyDataUpload implements Job {

    @Autowired
    DataReportService dataReportService;
    Logger logger= LoggerFactory.getLogger(YchzDataUploadJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //执行的方法
        try {
            logger.info("定时上传远程教育数据任务启动成功······");
            dataReportService.ReportDistanceLearByTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
