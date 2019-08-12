package hyc.upload.dataupload.uploadEntity;

import java.io.Serializable;
import java.util.Date;

public class Consultant implements Serializable {
    private static final long serialVersionUID = 1L;


    private String meetingOpinion;		//上级意见
    private String meetingDocName;		// 会诊医生姓名
    private String meetingSection;		// 上级科室名称
    private String isReferral;		// 是否转诊0不转，1转
    private String meetingTime;//会诊安排时间

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingOpinion() {
        return meetingOpinion;
    }

    public void setMeetingOpinion(String meetingOpinion) {
        this.meetingOpinion = meetingOpinion;
    }

    public String getMeetingDocName() {
        return meetingDocName;
    }

    public void setMeetingDocName(String meetingDocName) {
        this.meetingDocName = meetingDocName;
    }

    public String getMeetingSection() {
        return meetingSection;
    }

    public void setMeetingSection(String meetingSection) {
        this.meetingSection = meetingSection;
    }

    public String getIsReferral() {
        return isReferral;
    }

    public void setIsReferral(String isReferral) {
        this.isReferral = isReferral;
    }
}
