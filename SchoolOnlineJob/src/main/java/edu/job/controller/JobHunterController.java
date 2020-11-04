package edu.job.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.job.base.BaseController;
import edu.job.dto.SearchVo;
import edu.job.entity.HunterExperience;
import edu.job.entity.JobHunter;
import edu.job.service.HunterExperienceService;
import edu.job.service.JobHunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 求职者的Controller
 */
@RestController
@RequestMapping("/api/jobHunter")
public class JobHunterController extends BaseController {

    @Autowired
    private JobHunterService jobHunterService;

//    @Autowired
//    private ContractTemplate contractTemplate;

    @Autowired
    private HunterExperienceService hunterExperienceService;

    /**
     * 企业获取求职者列表（分页）
     * */
    @PostMapping("/doquery")
    private String doquery(@RequestBody String json){
        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);
        Map<String,Object> resultMap = jobHunterService.doquery(searchVo);
        /**
         * 在服务端输出json数据时按照一定的格式输出时间字段，fastjson支持两种方式：
         * 1.使用JSON.toJSONStringWithDateFormat方法
         * 2.JSON.toJSONString方法增加SerializerFeature.WriteDateUseDateFormat参数
         */
        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 企业获取求职者列表（分页）
     * */
    @PostMapping("/getHunterListByJobId")
    private String getHunterListByJobId(@RequestBody String json){
        SearchVo searchVo = JSONObject.parseObject(json, SearchVo.class);
        Map<String,Object> resultMap = jobHunterService.getHunterListByJobId(searchVo);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd HH:mm:ss",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }


    /**
     * 查看详情
     * */
    @GetMapping("/getJobHunterInfo/{hunterId}")
    private String getJobHunterInfo(@PathVariable(value = "hunterId") String hunterId){
        JobHunter jobHunter = jobHunterService.selectByPrimaryKey(hunterId);
        List<HunterExperience> projectExList = hunterExperienceService.getHunterExperienceByHunterIdAndType(Long.parseLong(hunterId), "XM");
        List<HunterExperience> workExList = hunterExperienceService.getHunterExperienceByHunterIdAndType(Long.parseLong(hunterId), "GZ");
        List<HunterExperience> educationExList = hunterExperienceService.getHunterExperienceByHunterIdAndType(Long.parseLong(hunterId), "JY");

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("jobHunter", jobHunter);
        resultMap.put("projectExList", projectExList);
        resultMap.put("workExList", workExList);
        resultMap.put("educationExList", educationExList);

        return JSONObject.toJSONStringWithDateFormat(resultMap,"yyyy-MM-dd",
                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

//    /**
//     * 企业获取求职者列表（分页）
//     * */
//    @GetMapping("/export/{userId}")
//    private String export(@PathVariable String userId){
//        String pdfPath = "";
//        try {
//            pdfPath = contractTemplate.generatePdf(userId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        } catch (com.lowagie.text.DocumentException e) {
//            e.printStackTrace();
//        }
//
//        Map<String,Object> map = new HashMap<>();
//        if(StringUtils.isEmpty(pdfPath)){
//            map.put("data", null);
//        }else{
//            map.put("data", "http://localhost:8787/" + pdfPath);
//        }
//
//        return JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss",
//                SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
//    }
}
