package edu.job.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.base.BaseController;
import edu.job.dto.SearchVo;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.Job;
import edu.job.entity.JobHunter;
import edu.job.entity.User;
import edu.job.service.EnterpriseInfoService;
import edu.job.service.JobHunterService;
import edu.job.service.JobRelationService;
import edu.job.utils.RequestCacheUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 职位关系的Controller
 */
@RestController
@RequestMapping("/api/jobRelation")
public class JobRelationController extends BaseController {

    @Autowired
    private JobRelationService jobRelationService;

    @Autowired
    private JobHunterService jobHunterService;

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    /**
     * 投递简历，申请职位
     * */
    @GetMapping("/apply/{id}")
    private String apply(@PathVariable String id, HttpServletRequest request){

        User user = (User) RequestCacheUtils.getCache(request.getHeader("token"));
        //求职者
//        JSONObject userObj = (JSONObject) jsonObject.get("user");
//        User user = userObj.toJavaObject(User.class);

        Map<String,Object> map = jobRelationService.apply(id, user);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业查看投递者列表
     * */
    @PostMapping("/listJobHunter")
    private String listJobHunter(@RequestBody String json, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
        searchVo.addCondition("enterpriseId", enterpriseInfo.getId());

        Map<String,Object> map = jobRelationService.listJobHunter2(searchVo);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业查看投递者信息
     * */
    @PostMapping("/listJobHunter/{jobId}")
    private String listJobHunter(@PathVariable String jobId, @RequestBody String json){
        JSONObject jsonObject = (JSONObject) JSON.parse(json);

        List<JobHunter> map = jobRelationService.listJobHunter(jobId, jsonObject);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 邀请面试
     * */
    @PostMapping("/invitation/{jobId}/{hunterId}")
    private String invitation(@PathVariable String jobId, @PathVariable String hunterId, @RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);

        Map<String,Object> resultMap = jobRelationService.invitation(jobId, hunterId, jsonObject.getString("interviewDate"));

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 邀请面试
     * */
    @PostMapping("/invitation2/{jobRelationId}")
    private String invitation2(@PathVariable String jobRelationId, @RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);

        Map<String,Object> resultMap = jobRelationService.invitation2(jobRelationId, jsonObject.getString("interviewDate"));

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 邀请面试
     * */
    @PostMapping("/notSee/{jobRelationId}")
    private String notSee(@PathVariable String jobRelationId){
        Map<String,Object> resultMap = jobRelationService.notSee(jobRelationId);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 查看邀请
     * */
    @PostMapping("/listInvitation")
    private String listInvitation(@RequestBody String json, HttpServletRequest request){

        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);
        JobHunter jobHunter = jobHunterService.selectByUserId(user.getId().toString());
        searchVo.addCondition("hunterId", jobHunter.getId());

        Map<String,Object> resultMap = jobRelationService.listInvitation(searchVo);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 接受面试邀请
     * */
    @PostMapping("/acceptInterview/{id}/{status}")
    private String acceptInterview(@PathVariable String id, @PathVariable String status){
        Map<String,Object> resultMap = jobRelationService.acceptInterview(id, status);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业公布面试结果
     * */
    @PostMapping("/updateInterviewResult/{id}/{passFlag}")
    private String updateInterviewResult(@PathVariable String id, @PathVariable String passFlag){
        Map<String,Object> resultMap = jobRelationService.updateInterviewResult(id, passFlag);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }



    /**
     * 求职者查看结果
     * */
    @PostMapping("/getInterviewResult/{id}")
    private String getInterviewResult(@PathVariable String id){
        Map<String,Object> resultMap = jobRelationService.getInterviewResult(id);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }



    /**
     * 评价面试
     * */
    @PostMapping("/evaluation/{id}")
    private String evaluation(@PathVariable String id, @RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String interviewEvaluation = jsonObject.getString("interviewEvaluation");

        Map<String,Object> resultMap = jobRelationService.evaluation(id, interviewEvaluation);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 企业公布面试结果
     * */
    @PostMapping("/cloudSign/{id}/{status}")
    private String cloudSign(@PathVariable String id, @PathVariable String status){
        Map<String,Object> resultMap = jobRelationService.cloudSign(id, status);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }
}
