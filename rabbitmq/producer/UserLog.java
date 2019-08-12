package hyc.upload.dataupload.rabbitmq.producer;/**
 * @ClassName UserLog
 * @Description TODO
 * @Author DL
 * @Date 2019/8/6 18:21
 * @Version 1.0
 */

import java.io.Serializable;

/**
 * @ClassName UserLog
 * @Description TODO
 * @Author DL
 * @Date 2019/8/6 18:21
 * @Version 1.0
 */

public class UserLog implements Serializable{

    private String userName;
    private String content;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

