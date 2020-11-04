package edu.job.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.base.BaseController;
import edu.job.dto.SearchVo;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.ExplainMeeting;
import edu.job.entity.User;
import edu.job.enumeration.UserEnum;
import edu.job.service.ExplainMeetingService;
import edu.job.utils.RequestCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 宣讲会
 */
@RestController
@RequestMapping("/api/explainMeeting")
public class ExplainMeetingController extends BaseController {

    @Autowired
    private ExplainMeetingService explainMeetingService;

    /**
     * 分页列表
     * */
    @PostMapping("/doquery")
    public String doquery(@RequestBody String json, HttpServletRequest request){
        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);

        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        Map<String,Object> resultMap = explainMeetingService.doquery(searchVo, user);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 保存
     * */
    @PostMapping("/save")
    public String save(@RequestBody String json, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        ExplainMeeting explainMeeting = JSON.parseObject(json).toJavaObject(ExplainMeeting.class);

        Map<String,Object> resultMap = explainMeetingService.save(explainMeeting, user);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 删除
     * */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        Map<String,Object> resultMap = explainMeetingService.delete(id, user);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 查看详情
     * */
    @GetMapping("/view/{id}")
    public String view(@PathVariable String id){
        Map<String,Object> resultMap = explainMeetingService.view(id);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }
}
