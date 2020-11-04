package edu.job.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.base.BaseController;
import edu.job.dto.SearchVo;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.Job;
import edu.job.entity.User;
import edu.job.service.EnterpriseInfoService;
import edu.job.service.JobService;
import edu.job.utils.RequestCacheUtils;
import org.bouncycastle.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业信息
 */
@RestController
@RequestMapping("/api/enterpriseInfo")
public class EnterpriseInfoController extends BaseController {

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    @Autowired
    private JobService jobService;

    /**
     * 企业列表（分页）
     * */
    @PostMapping("/doquery")
    private String doquery(@RequestBody String json, HttpServletRequest request){

        User user = (User) RequestCacheUtils.getCache(request.getHeader("token"));

        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);
        Map<String,Object> resultMap = enterpriseInfoService.doquery(searchVo, user);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     *
     * */
    @GetMapping("/view/{id}")
    private String view(@PathVariable String id){
//        Map<String,Object> resultMap = enterpriseInfoService.deleteEnterprise(id);

        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByPrimaryKey(id);
        List<Job> jobList = jobService.selectByEnterpriseId(id);
        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseInfo", enterpriseInfo);
        map.put("jobList", jobList);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    @GetMapping("/getEnterpriseInfo")
    private String getEnterpriseInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseInfo", enterpriseInfo);
        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 删除
     * */
    @PostMapping("/delete/{id}")
    private String delete(@PathVariable String id){
//        Map<String,Object> resultMap = enterpriseInfoService.deleteEnterprise(id);

        return JSONObject.toJSONStringWithDateFormat(null,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 审核
     * */
    @GetMapping("/audit/{id}/{status}")
    private String audit(@PathVariable String id, @PathVariable String status, HttpServletRequest request){
        String statusDesc = "1".equals(status) ? "审核通过" : "审核驳回";

        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        return JSONObject.toJSONStringWithDateFormat(enterpriseInfoService.audit(id, statusDesc, user),"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }
}
