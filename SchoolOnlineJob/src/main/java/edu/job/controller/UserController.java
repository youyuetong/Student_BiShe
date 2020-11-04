package edu.job.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.base.BaseController;
import edu.job.dto.user.UserDTO;
import edu.job.entity.User;
import edu.job.service.UserService;
import edu.job.utils.RequestCacheUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import sun.misc.UUDecoder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户的Controller
 */
@RestController
public class UserController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 登录
     * */
    @PostMapping("/user/login")
    private String login(@RequestBody String json){
        JSONObject jsonObject = (JSONObject) JSON.parse(json);
        String loginName = jsonObject.getString("loginName");
        String password = jsonObject.getString("password");

        UserDTO user = userService.checkValid(loginName,password);
        Map<String, Object> map = new HashMap<>();
        if(user == null){
            map.put("result", false);
            map.put("message", "用户不存在或者密码错误");
        }else{
            map.put("result", true);
            map.put("message", "登录成功");

            String token = UUID.randomUUID().toString().replaceAll("-", "");
            RequestCacheUtils.setCache(token, user);

            map.put("data", user);
            map.put("token", token);
        }
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 注册
     * */
    @PostMapping("/user/register")
    private String register(@RequestBody String json){
        JSONObject jsonObject = (JSONObject) JSON.parse(json);
        UserDTO user = jsonObject.toJavaObject(UserDTO.class);

        Map<String,Object> map = userService.register(user);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 注册
     * */
    @PostMapping("/user/validatePassword")
    private String validatePassword(@RequestBody String json){
        JSONObject jsonObject = (JSONObject) JSON.parse(json);
        User user = jsonObject.toJavaObject(User.class);

        Map<String,Object> map = userService.validatePassword(user);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 修改密码
     * */
    @PostMapping("/api/user/changePassword")
    private String changePassword(@RequestBody String json){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String oldPassword = jsonObject.getString("oldPassword");
        String newPassword = jsonObject.getString("newPassword");
        userDTO.setOldPassword(oldPassword);
        userDTO.setNewPassword(newPassword);

        Map<String,Object> map = userService.changePassword(userDTO);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 查看个人信息
     * */
    @GetMapping("/api/user/view/{userId}")
    private String viewUserInfo(@PathVariable String userId){
        Map<String,Object> map = userService.viewUserInfo(userId);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 查看个人信息
     * */
    @GetMapping("/api/user/information")
    private String viewUserInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        Map<String,Object> map = userService.viewUserInfo(user.getId().toString());
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 修改/完善个人信息
     * */
    @PostMapping("/api/user/update")
    private String updateUserInfo(@RequestBody String json, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        JSONObject jsonObject = (JSONObject) JSON.parse(json);
        jsonObject.put("basic", user);

        Map<String,Object> map = userService.updateUserInfo(jsonObject);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    @PostMapping("/api/user/updateWechatId")
    private String updateWechatId(@RequestBody String json, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);
        JSONObject jsonObject = (JSONObject) JSON.parse(json);

        Map<String,Object> map = userService.updateWechatId(jsonObject, user);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    @PostMapping("/api/user/updateEmail")
    private String updateEmail(@RequestBody String json, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);
        JSONObject jsonObject = (JSONObject) JSON.parse(json);

        Map<String,Object> map = userService.updateEmail(jsonObject, user);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 修改个人设置
     * */
    @GetMapping("/api/user/changeSetting/{userId}/{canSearch}")
    private String changeSetting(@PathVariable String userId,@PathVariable String canSearch){
        Map<String,Object> map = userService.changeSetting(userId, canSearch);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

}
