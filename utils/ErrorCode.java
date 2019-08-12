package hyc.upload.dataupload.utils;

/**
 * @ClassName ErrorCode
 * @Description TODO
 * @Author DL
 * @Date 2019/6/26 15:21
 * @Version 1.0
 */
public enum ErrorCode {
    ERROR_CODE("001","系统错误");
    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
