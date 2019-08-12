package hyc.upload.dataupload.uploadEntity;
//远程会诊上传得数据对象

import java.io.Serializable;
import java.util.Date;

public class RemoteConsultationEntity implements Serializable {


    private static final long serialVersionUID = 1L;


    private String clientId;//第三方厂商标识
    private String accessToken;//接口调用凭证
    private String thirdUniqueid;//唯一id
    private String channelName;//渠道
    private String applyOrgName;//下级机构名称
    private String applyDocName;//下级医生姓名
    private String applySection;//下级科室名称
    private String applyDiagnosis;//下级诊断
    private String applyDateTime;//申请时间
    private String patientName;//患者姓名
    private String patientSex;//患者性别
    private String patientAge;//患者年龄
    private String patientIdcardNum;//患者身份证号码
    private String meetingTime;//会诊开始时间
    private String meetingOrgName;//上级机构名称
    private String meetingOrgCode;//上级机构编码
    private String meetingOpinion;//上级意见
    private String meetingDocName ;//上级医生姓名
    private String meetingDocTitle ;//上级医生职称
    private String meetingSection  ;//上级科室名称
    private String meetingSectionCode ;//科室编码
    private String meetingType ;//会诊类型
    private String isReferral ;//是否转诊
    private  String meetingNum;//会诊编号
    private String subject ;//会诊科目
    private String applyCityId ;//下级机构城市id（参考地区字段）


    private  String HZXZ;//查询用的会诊性质
    private  String HZLX;//查询用的会诊类型

    public String getHZXZ() {
        return HZXZ;
    }

    public void setHZXZ(String HZXZ) {
        this.HZXZ = HZXZ;
    }

    public String getHZLX() {
        return HZLX;
    }

    public void setHZLX(String HZLX) {
        this.HZLX = HZLX;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public String getApplyOrgName() {
        return applyOrgName;
    }

    public void setApplyOrgName(String applyOrgName) {
        this.applyOrgName = applyOrgName;
    }

    public String getApplyDocName() {
        return applyDocName;
    }

    public void setApplyDocName(String applyDocName) {
        this.applyDocName = applyDocName;
    }

    public String getApplySection() {
        return applySection;
    }

    public void setApplySection(String applySection) {
        this.applySection = applySection;
    }

    public String getApplyDiagnosis() {
        return applyDiagnosis;
    }

    public void setApplyDiagnosis(String applyDiagnosis) {
        this.applyDiagnosis = applyDiagnosis;
    }

    public String getApplyDateTime() {
        return applyDateTime;
    }

    public void setApplyDateTime(String applyDateTime) {
        this.applyDateTime = applyDateTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientIdcardNum() {
        return patientIdcardNum;
    }

    public void setPatientIdcardNum(String patientIdcardNum) {
        this.patientIdcardNum = patientIdcardNum;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingOrgName() {
        return meetingOrgName;
    }

    public void setMeetingOrgName(String meetingOrgName) {
        this.meetingOrgName = meetingOrgName;
    }

    public String getMeetingOrgCode() {
        return meetingOrgCode;
    }

    public void setMeetingOrgCode(String meetingOrgCode) {
        this.meetingOrgCode = meetingOrgCode;
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

    public String getMeetingDocTitle() {
        return meetingDocTitle;
    }

    public void setMeetingDocTitle(String meetingDocTitle) {
        this.meetingDocTitle = meetingDocTitle;
    }

    public String getMeetingSection() {
        return meetingSection;
    }

    public void setMeetingSection(String meetingSection) {
        this.meetingSection = meetingSection;
    }

    public String getMeetingSectionCode() {
        return meetingSectionCode;
    }

    public void setMeetingSectionCode(String meetingSectionCode) {
        this.meetingSectionCode = meetingSectionCode;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getIsReferral() {
        return isReferral;
    }

    public void setIsReferral(String isReferral) {
        this.isReferral = isReferral;
    }

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getApplyCityId() {
        return applyCityId;
    }

    public void setApplyCityId(String applyCityId) {
        this.applyCityId = applyCityId;
    }

    @Override
    public String toString() {
        return "RemoteConsultationDao{" +
                "thirdUniqueid='" + thirdUniqueid + '\'' +
                ", channelName='" + channelName + '\'' +
                ", applyOrgName='" + applyOrgName + '\'' +
                ", applyDocName='" + applyDocName + '\'' +
                ", applySection='" + applySection + '\'' +
                ", applyDiagnosis='" + applyDiagnosis + '\'' +
                ", applyDateTime='" + applyDateTime + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", patientAge='" + patientAge + '\'' +
                ", patientIdcardNum='" + patientIdcardNum + '\'' +
                ", meetingTime='" + meetingTime + '\'' +
                ", meetingOrgName='" + meetingOrgName + '\'' +
                ", meetingOrgCode='" + meetingOrgCode + '\'' +
                ", meetingOpinion='" + meetingOpinion + '\'' +
                ", meetingDocName='" + meetingDocName + '\'' +
                ", meetingDocTitle='" + meetingDocTitle + '\'' +
                ", meetingSection='" + meetingSection + '\'' +
                ", meetingSectionCode='" + meetingSectionCode + '\'' +
                ", meetingType='" + meetingType + '\'' +
                ", isReferral='" + isReferral + '\'' +
                ", subject='" + subject + '\'' +
                ", applyCityId='" + applyCityId + '\'' +
                '}';
    }
}
