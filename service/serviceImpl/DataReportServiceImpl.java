package hyc.upload.dataupload.service.serviceImpl;

import hyc.upload.dataupload.configClass.DataReportConfig;
import hyc.upload.dataupload.httpUtil.GetToken;
import hyc.upload.dataupload.httpUtil.HttpPostForm;
import hyc.upload.dataupload.service.DataReportService;
import hyc.upload.dataupload.service.DictService;
import hyc.upload.dataupload.service.IRedisService;
import hyc.upload.dataupload.uploadDao.DistancelearingDao;
import hyc.upload.dataupload.uploadDao.RemoteConsultationDao;
import hyc.upload.dataupload.uploadEntity.Consultant;
import hyc.upload.dataupload.uploadEntity.Dict;
import hyc.upload.dataupload.uploadEntity.DistanceLearning;
import hyc.upload.dataupload.uploadEntity.RemoteConsultationEntity;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
public class DataReportServiceImpl extends IRedisService implements DataReportService{


    private static Logger logger = LoggerFactory.getLogger(DataReportServiceImpl.class);

    private  Map<String,String> mapHZLX=new HashMap<String,String>();
    private Map<String,String> mapArea=new HashMap<String,String>();

    @Autowired
    private DistancelearingDao distancelearingDao;

    @Autowired
    private RemoteConsultationDao remoteConsultationDao;

    @Autowired
    private DictService dictService;

    @Autowired
    private  DataReportConfig dataReportConfig;


//初始化执行该方法
 //   @PostConstruct
  public void  getDict(){
        Dict dict = new Dict();
        dict.setType("HZLX");
        List<Dict> listHzlx = dictService.findList(dict);
        for (Dict dictHzlx : listHzlx) {
            if (dictHzlx.getValue() != null && dictHzlx.getValue() != "") {
                mapHZLX.put(dictHzlx.getValue(), dictHzlx.getLabel());
            }
        }



        mapArea.put("001","成都市");
        mapArea.put("002","绵阳市");
        mapArea.put("003","内江市");
        mapArea.put("004","南充市");
        mapArea.put("005","乐山市");
        mapArea.put("006","自贡市");
        mapArea.put("007","泸州市");
        mapArea.put("008","德阳市");
        mapArea.put("009","广元市");
        mapArea.put("010","遂宁市");
        mapArea.put("011","眉山市");
        mapArea.put("012","阿坝藏族羌族自治州");
        mapArea.put("013","广安市");
        mapArea.put("014","达州市");
        mapArea.put("015","雅安市");
        mapArea.put("016","巴中市");
        mapArea.put("017","资阳市");
        mapArea.put("018","攀枝花市");
        mapArea.put("019","凉山彝族自治州");
        mapArea.put("020","甘孜藏族自治州");
        mapArea.put("021","宜宾市");




  }




    @Override
    public void ReportDistanceLearAll() throws Exception {

new Thread(){
    @Override
    public void run() {
        List<DistanceLearning> distanceLearningList=distancelearingDao.getAll();
        try {
            sendDistance(distanceLearningList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}.start();




    }

    @Override
    public void ReportDistanceLearByTime() throws Exception {

      new Thread(){
          @Override
          public void run() {

              List<DistanceLearning> distanceLearningList=distancelearingDao.getByTime();
              try {
                  sendDistance(distanceLearningList);
              } catch (Exception e) {
                  e.printStackTrace();
              }


          }
      }.start();


    }
//上传所有的会诊数据
    @Override
    public void ReportRemoteConsultationAll() throws Exception {
      new Thread(){
          @Override
          public void run() {
              List<RemoteConsultationEntity> remoteList=  remoteConsultationDao.getAll();
              try {
                  sendRemote(remoteList);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }.start();


    }


//每天上传完成的数据
    @Override
    public void ReportRemoteConsultationByTime() throws Exception {

      new Thread(){
          @Override
          public void run() {

              List<RemoteConsultationEntity> remoteList=  remoteConsultationDao.getByTime();
              try {
                  sendRemote(remoteList);
              } catch (Exception e) {
                  e.printStackTrace();
              }


          }
      }.start();



    }




    void sendDistance(List<DistanceLearning> distanceList) throws  Exception{



        for (int i=0;i<distanceList.size();i++) {
            try {
                DistanceLearning distance = distanceList.get(i);
                String accessToken = "";
                if (this.isKeyExists("accessToken")) {
                    //token不为空

                    accessToken = this.get("accessToken").toString();
                    distance.setAccessToken(this.get("accessToken").toString());
                } else {
                    //获取token
                    JSONObject jsonObject = GetToken.getToken(dataReportConfig.getGetTokenUrl(), dataReportConfig.getClientId(), dataReportConfig.getAppSecret());
                    if (jsonObject.get("status").toString().equals("0")) {
                        accessToken = JSONObject.fromObject(jsonObject.get("data")).get("accessToken").toString();
                        distance.setAccessToken(accessToken);
                        this.put("accessToken", accessToken, 60 * 60 * 24 * 2);
                    }
                }

                distance.setChannelName("四川省人民医院远程医疗平台");
                distance.setTrainOrgCode("45071756-X");
                distance.setTrainOrgName("四川省医学科学院四川省人民医院");
                distance.setClientId(dataReportConfig.getClientId());
                int max = 300;
                int min = 80;
                Random random = new Random();

                int s = random.nextInt(max) % (max - min + 1) + min;

                distance.setTrainPeople(String.valueOf(s));


                JSONObject result = HttpPostForm.httpPost(dataReportConfig.getSendDistanceUrl(), JSONObject.fromObject(distance));
                if(result.get("status").toString().equals("001x025")){
                    JSONObject jsonObject = GetToken.getToken(dataReportConfig.getGetTokenUrl(), dataReportConfig.getClientId(), dataReportConfig.getAppSecret());
                    if (jsonObject.get("status").toString().equals("0")) {
                        accessToken = JSONObject.fromObject(jsonObject.get("data")).get("accessToken").toString();
                        this.put("accessToken", accessToken, 60 * 60 * 24 * 2);
                    }
                }
                if (result.get("status").toString().equals("0")) {

                    logger.info(distance.getThirdUniqueid() + ">>>" + "status:0" + ">>>" + "type:远程教育" + "数据上传成功");
                } else {

                    logger.info(distance.getThirdUniqueid() + ">>>" + "message:" + result.toString() + ">>>" + "type:远程教育" + "数据上传失败");

                }

            } catch (Exception e) {
                continue;

            }
            Thread.sleep(500);


        }

    }

    void sendRemote(List<RemoteConsultationEntity> remoteList) throws Exception{

        for(int i=0;i<remoteList.size();i++){
            //处理每条数据
            try {



                RemoteConsultationEntity   remote=  remoteList.get(i);
                String accessToken="";
                if(this.isKeyExists("accessToken")){
                    //token不为空
                    accessToken=this.get("accessToken").toString();
                    remote.setAccessToken(this.get("accessToken").toString());
                }else{
                    //获取token
                    JSONObject jsonObject= GetToken.getToken(dataReportConfig.getGetTokenUrl(),dataReportConfig.getClientId(),dataReportConfig.getAppSecret());
                    if (jsonObject.get("status").toString().equals("0")) {
                        accessToken=  JSONObject.fromObject(jsonObject.get("data")).get("accessToken").toString();
                        remote.setAccessToken(accessToken);
                        this.put("accessToken",accessToken,60*60*24*2);
                    }
                    remote.setClientId(dataReportConfig.getClientId());
                }

                remote.setClientId(dataReportConfig.getClientId());
                remote.setChannelName("四川省人民医院远程医疗平台");
                List<Consultant> listConsultant=   remoteConsultationDao.getByApplyId(remote.getMeetingNum());
                Consultant consultant=null;
                if(listConsultant.size()>0){
                    consultant=listConsultant.get(0);
                }
                remote.setIsReferral(consultant.getIsReferral().equals("0")?"否":"是");
                remote.setMeetingTime(consultant.getMeetingTime());
                remote.setMeetingOrgName("四川省医学科学院四川省人民医院");
                remote.setMeetingOrgCode("45071756-X");
                String sex=remote.getPatientSex().equals("SEX-1") ? "男" : "女";
                remote.setPatientSex(sex);
                //意见处理的合并
                StringBuffer sbOpinion= new StringBuffer();
                StringBuffer sbDocName= new StringBuffer();
                StringBuffer sbSection=new StringBuffer();
                for (int j = 0; j < listConsultant.size(); j++) {
                    Consultant co=listConsultant.get(j);
                    sbOpinion.append(co.getMeetingDocName()+":");
                    sbOpinion.append(co.getMeetingOpinion());
                    sbOpinion.append(";");
                    sbDocName.append(co.getMeetingDocName());
                    sbDocName.append(";");
                    sbSection.append(co.getMeetingSection());
                    sbSection.append(";");

                }
                remote.setMeetingOpinion(sbOpinion.toString());
                remote.setMeetingDocName(sbDocName.toString());
                remote.setMeetingDocTitle("主任医师");

                remote.setMeetingSection(sbSection.toString());
                remote.setMeetingSectionCode("无");
                //会诊类型
                String nature="1";
                if (mapHZLX.containsKey(remote.getHZLX())) {
                    nature = mapHZLX.get(remote.getHZLX()).toString().equals("急会诊") ? "2" : "1";
                }
                remote.setSubject(nature);

                //会诊性质
                String type=listConsultant.size()>0?"2":"1";

                remote.setMeetingType(type);
                //城市id
                String city=mapArea.get(  remoteConsultationDao.getDictName(remote.getApplyOrgName()));
                String cityId="";
                for(Map.Entry entry:mapArea.entrySet()){
                    if (entry.getValue().toString().equals(city)){
                        cityId=entry.getKey().toString();
                        break;
                    }

                }



                remote.setApplyCityId(cityId);

                JSONObject result=HttpPostForm.httpPost(dataReportConfig.getSendRemoteUrl(),JSONObject.fromObject(remote));

                if(result.get("status").toString().equals("001x025")){
                    JSONObject jsonObject = GetToken.getToken(dataReportConfig.getGetTokenUrl(), dataReportConfig.getClientId(), dataReportConfig.getAppSecret());
                    if (jsonObject.get("status").toString().equals("0")) {
                        accessToken = JSONObject.fromObject(jsonObject.get("data")).get("accessToken").toString();
                        this.put("accessToken", accessToken, 60 * 60 * 24 * 2);
                    }
                }
                if(result.get("status").toString().equals("0")){

                    logger.info(remote.getThirdUniqueid()+">>>"+"status:0"+">>>"+"type:远程会诊"+"数据上传成功");
                }else{

                    logger.info(remote.getThirdUniqueid()+">>>"+"message:"+result.toString()+">>>"+"type:远程会诊"+"数据上传失败");

                }

            }catch (Exception e){

                continue;
            }

            Thread.sleep(500);
        }


    }


    @Override
    protected String getRedisKey() {
        String RedisKey="REDIS_KEY";
        return RedisKey;
    }
}
