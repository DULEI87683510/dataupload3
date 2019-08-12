package hyc.upload.dataupload.uploadEntity;


import java.io.Serializable;
import java.util.Date;

public class DistanceLearning implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  thirdUniqueid;//唯一id标示
    private String channelName;//渠道
    private String trainOrgCode;//培训机构编码
    private String trainOrgName;//培训机构名称
    private String trainTime;//培训时间
    private String trainProject;//培训名称
    private String trainPeople;//培训人次
    private String accessToken;//token
    private String clientId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getThirdUniqueid() {
        return thirdUniqueid;
    }

    public void setThirdUniqueid(String thirdUniqueid) {
        this.thirdUniqueid = thirdUniqueid;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTrainOrgCode() {
        return trainOrgCode;
    }

    public void setTrainOrgCode(String trainOrgCode) {
        this.trainOrgCode = trainOrgCode;
    }

    public String getTrainOrgName() {
        return trainOrgName;
    }

    public void setTrainOrgName(String trainOrgName) {
        this.trainOrgName = trainOrgName;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public String getTrainProject() {
        return trainProject;
    }

    public void setTrainProject(String trainProject) {
        this.trainProject = trainProject;
    }

    public String getTrainPeople() {
        return trainPeople;
    }

    public void setTrainPeople(String trainPeople) {
        this.trainPeople = trainPeople;
    }

    @Override
    public String toString() {
        return "DistanceLearning{" +
                "thirdUniqueid='" + thirdUniqueid + '\'' +
                ", channelName='" + channelName + '\'' +
                ", trainOrgCode='" + trainOrgCode + '\'' +
                ", trainOrgName='" + trainOrgName + '\'' +
                ", trainTime='" + trainTime + '\'' +
                ", trainProject='" + trainProject + '\'' +
                ", trainPeople='" + trainPeople + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
