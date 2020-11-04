package edu.job.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResponseUtil {

    public static String getResponseResult(Object obj){
        return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

}
