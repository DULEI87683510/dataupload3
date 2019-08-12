package hyc.upload.dataupload.configClass;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "datareport")
@PropertySource(value = "classpath:DataReport-${spring.profiles.active}.properties")

public class DataReportConfig {
    private     String clientId;
    private    String appSecret;
    private     String getTokenUrl;//获取token的地址
    private     String sendRemoteUrl;//上传远程会诊的数据地址
    private     String sendDistanceUrl;//上传远程教育的数据地址
    private    String sendDistanceCron;// 教育的cron表达式
    private    String sendRemoteCron;// 远程会诊的cron的表达式

    public String getSendDistanceCron() {
        return sendDistanceCron;
    }

    public void setSendDistanceCron(String sendDistanceCron) {
        this.sendDistanceCron = sendDistanceCron;
    }

    public String getSendRemoteCron() {
        return sendRemoteCron;
    }

    public void setSendRemoteCron(String sendRemoteCron) {
        this.sendRemoteCron = sendRemoteCron;
    }

    public String getSendDistanceUrl() {
        return sendDistanceUrl;
    }

    public void setSendDistanceUrl(String sendDistanceUrl) {
        this.sendDistanceUrl = sendDistanceUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getGetTokenUrl() {
        return getTokenUrl;
    }

    public void setGetTokenUrl(String getTokenUrl) {
        this.getTokenUrl = getTokenUrl;
    }

    public String getSendRemoteUrl() {
        return sendRemoteUrl;
    }

    public void setSendRemoteUrl(String sendRemoteUrl) {
        this.sendRemoteUrl = sendRemoteUrl;
    }

    @Override
    public String toString() {
        return "DataReportConfig{" +
                "clientId='" + clientId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", getTokenUrl='" + getTokenUrl + '\'' +
                '}';
    }
}
