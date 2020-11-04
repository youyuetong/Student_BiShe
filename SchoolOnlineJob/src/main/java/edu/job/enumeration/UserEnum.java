package edu.job.enumeration;

import com.alibaba.druid.util.StringUtils;
import edu.job.dto.CodeDescVo;

import java.util.ArrayList;
import java.util.List;

public enum UserEnum {
//    性别（男1，女2）
    USER_TYPE_ADMIN("A","管理员"),
    USER_TYPE_ENTERPRISE("E","企业用户"),
    USER_TYPE_JOB_HUNTER("H", "求职者");


    public static List<UserEnum> getUserTypeList(){
        List<UserEnum> enums =new ArrayList<>();
        enums.add(UserEnum.USER_TYPE_ADMIN);
        enums.add(UserEnum.USER_TYPE_ENTERPRISE);
        enums.add(UserEnum.USER_TYPE_JOB_HUNTER);
        return enums;
    }

    public static String getUserType(String code){
        if (StringUtils.isEmpty(code)) {
            return code;
        }
        for(UserEnum item : getUserTypeList()){
            if(code.equals(item.code)){
                return item.getText();
            }
        }
        return code;
    }

    //转换成List
    public static List<CodeDescVo> convertCodeDescList(List<UserEnum> list){
        List<CodeDescVo> result = new ArrayList<>();
        list.stream().forEach(item->{
            result.add(new CodeDescVo(item.getCode(),item.getText()));
        });
        return result;
    }

    private String code;
    private String text;

    UserEnum(String code, String text) {
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
