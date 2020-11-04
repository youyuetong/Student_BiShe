package edu.job.dto;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.enumeration.RequestStatusEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;


public class RequestResult<T> {
    // 响应业务状态
    private String status;
    // 响应消息
    private String msg;
    // 响应的数据
    private T data;
    // 额外参数
    private Map<String, Object> extraData;

    public RequestResult() {
        super();
    }

    public RequestResult(String status, String msg, T data, Map<String, Object> extraData) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.extraData = extraData;
    }

    public RequestResult(String status, String msg, T data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public RequestResult(String status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public RequestResult(String msg, T data, Map<String, Object> extraData) {
        super();
        this.msg = msg;
        this.data = data;
        this.extraData = extraData;
    }

    public RequestResult(String msg, T data) {
        super();
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    public static RequestResult failure() {
        return new RequestResult(RequestStatusEnum.FAILURE.getCode(), RequestStatusEnum.FAILURE.getText());
    }

    public static RequestResult failure(String message) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.FAILURE.getText();
        }
        return new RequestResult(RequestStatusEnum.FAILURE.getCode(), message);
    }

    public static RequestResult failure(String message, Object data) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.FAILURE.getText();
        }
        return new RequestResult(RequestStatusEnum.FAILURE.getCode(), message, data);
    }

    public static RequestResult failure(String message, Object data, Map<String, Object> extraData) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.FAILURE.getText();
        }
        return new RequestResult(RequestStatusEnum.FAILURE.getCode(), message, data, extraData);
    }

    public static RequestResult success() {
        return new RequestResult(RequestStatusEnum.SUCCESS.getCode(), RequestStatusEnum.SUCCESS.getText());
    }

    public static RequestResult success(Object data) {
        return new RequestResult(RequestStatusEnum.SUCCESS.getCode(), RequestStatusEnum.SUCCESS.getText(), data);
    }

    public static RequestResult success(String message, Object data) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.SUCCESS.getText();
        }
        return new RequestResult(RequestStatusEnum.SUCCESS.getCode(), message, data);
    }

    public static RequestResult success(String message, Object data, Map<String, Object> extraData) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.SUCCESS.getText();
        }
        return new RequestResult(RequestStatusEnum.SUCCESS.getCode(), message, data, extraData);
    }

    public static RequestResult error() {
        return new RequestResult(RequestStatusEnum.ERROR.getCode(), RequestStatusEnum.ERROR.getText());
    }

    public static RequestResult error(String message) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.ERROR.getText();
        }
        return new RequestResult(RequestStatusEnum.ERROR.getCode(), message);
    }

    public static RequestResult error(String message, Object data) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.ERROR.getText();
        }
        return new RequestResult(RequestStatusEnum.ERROR.getCode(), message, data);
    }

    public static RequestResult error(String message, Object data, Map<String, Object> extraData) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.ERROR.getText();
        }
        return new RequestResult(RequestStatusEnum.ERROR.getCode(), message, data, extraData);
    }

    public static RequestResult accessdenied() {
        return new RequestResult(RequestStatusEnum.ACCESSDENIED.getCode(), RequestStatusEnum.ACCESSDENIED.getText());
    }

    public static RequestResult accessdenied(String message) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.ACCESSDENIED.getText();
        }
        return new RequestResult(RequestStatusEnum.ACCESSDENIED.getCode(), message);
    }

    public static RequestResult accessdenied(String message, Object data) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.ACCESSDENIED.getText();
        }
        return new RequestResult(RequestStatusEnum.ACCESSDENIED.getCode(), message, data);
    }

    public static RequestResult accessdenied(String message, Object data, Map<String, Object> extraData) {
        if (StringUtils.isBlank(message)) {
            message = RequestStatusEnum.ACCESSDENIED.getText();
        }
        return new RequestResult(RequestStatusEnum.ACCESSDENIED.getCode(), message, data, extraData);
    }

    public String toJson() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
    }
}
