package edu.job.enumeration;

public enum RequestStatusEnum {
    SUCCESS("1", "成功"),
    FAILURE("0", "失败"),
    ERROR("500", "错误"),
    ACCESSDENIED("401", "未授权");

    private String code;
    private String text;

    RequestStatusEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
