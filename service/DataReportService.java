package hyc.upload.dataupload.service;

public interface DataReportService {
    //导出所有的远程教育数据上传到到卫计委
    public void ReportDistanceLearAll() throws Exception;
   //导出某个时间段远程教育的所有数据
    public void ReportDistanceLearByTime() throws Exception;
    //导出所有远程会诊的数据
    public void ReportRemoteConsultationAll() throws Exception;
    //导出远程会诊的数据根据时间段
    public void  ReportRemoteConsultationByTime() throws Exception;



}
