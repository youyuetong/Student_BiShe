package edu.job.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.base.BaseController;
import edu.job.dto.SearchVo;
import edu.job.entity.Job;
import edu.job.entity.User;
import edu.job.service.JobService;
import edu.job.utils.RequestCacheUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 职位的Controller
 */
@RestController
@RequestMapping("/api/job")
public class JobController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobService jobService;


    /**
     * 企业查看职位
     * */
    @GetMapping("/viewNew/{userId}")
    private String viewNew(@PathVariable String userId){
        Map<String,Object> map = jobService.viewNew(userId);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业查看职位
     * */
    @GetMapping("/view/{id}")
    private String view(@PathVariable String id){
        Map<String,Object> map = jobService.view(id);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业查看职位
     * */
    @GetMapping("/view2/{id}")
    private String view2(@PathVariable String id){
        Map<String,Object> map = jobService.view2(id);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业保存职位
     * */
    @PostMapping("/save")
    private String save(@RequestBody String json, HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        JSONObject jsonObject = (JSONObject) JSON.parse(json);

        Job job = jsonObject.toJavaObject(Job.class);
        Map<String,Object> map = jobService.save(job, user);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业发布职位
     * */
    @GetMapping("/publish/{id}")
    private String publish(@PathVariable String id){
        Map<String,Object> map = jobService.publish(id);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业发布职位
     * */
    @GetMapping("/unpublish/{id}")
    private String unpublish(@PathVariable String id){
        Map<String,Object> map = jobService.unpublish(id);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业发布职位
     * */
    @GetMapping("/deleteJob/{id}")
    private String deleteJob(@PathVariable String id){
        Map<String,Object> map = jobService.deleteJob(id);

        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 求职者查询职位列表（分页）
     * */
    @PostMapping("/doquery")
    private String doquery(@RequestBody String json, HttpServletRequest request){
        User user = (User) RequestCacheUtils.getCache(request.getHeader("token"));

        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);
        Map<String,Object> resultMap = jobService.doquery(searchVo, user);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    @GetMapping("/getList/{userId}")
    private String getList(@PathVariable String userId){
        Map<String,Object> resultMap = jobService.getList(userId);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    @GetMapping("/getList")
    private String getList(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) RequestCacheUtils.getCache(token);

        Map<String,Object> resultMap = jobService.listAll(user.getId().toString());

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业删除职位
     * */
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable String id){
        Map<String,Object> resultMap = jobService.delete(id);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }
}
