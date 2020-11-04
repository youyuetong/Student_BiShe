package edu.job.service;

import com.alibaba.fastjson.JSONObject;
import edu.job.base.BaseService;
import edu.job.dto.SearchVo;
import edu.job.dto.job.JobRelationDTO;
import edu.job.entity.Job;
import edu.job.entity.JobHunter;
import edu.job.entity.JobRelation;
import edu.job.entity.User;
import edu.job.mapper.JobRelationMapper;
import edu.job.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 求职Service
 */
@Service
public class JobRelationService extends BaseService<JobRelation> {

    @Autowired
    private JobRelationMapper jobRelationMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobHunterService jobHunterService;

    public Map<String, Object> apply(String id, User user) {
        Job jobOld = jobService.selectByPrimaryKey(id);
        Long hunterId = user.getId();

        JobHunter hunter = jobHunterService.selectByUserId(hunterId.toString());

        Long interviewId = jobOld.getPublishedId(); //面试官Id

        JobRelation jobRelation = new JobRelation();
        jobRelation.setId(IDGenerator.generateId());
        jobRelation.setJobId(jobOld.getId());
        jobRelation.setHunterId(hunter.getId());
        jobRelation.setInterviewId(interviewId);

        jobRelation.setApplyTime(new Date());
        jobRelation.setCreateTime(new Date());

        int count = jobRelationMapper.insert(jobRelation);

        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", "申请面试");
        return result;
    }

    public List<JobHunter> listJobHunter(String jobId, JSONObject jsonObject) {
        List<JobHunter> list = jobHunterService.listJobHunter(jobId, jsonObject);
        return list;
    }

    public Map<String, Object> invitation(String jobId, String hunterId, String interviewTime) {
        Job jobOld = jobService.selectByPrimaryKey(jobId);
        Long interviewId = jobOld.getPublishedId(); //面试官Id

        JobRelation jobRelation = new JobRelation();
        jobRelation.setId(IDGenerator.generateId());
        jobRelation.setJobId(Long.valueOf(jobId));
        jobRelation.setHunterId(Long.valueOf(hunterId));
        jobRelation.setInterviewId(interviewId);

        jobRelation.setCanInterview("I");//邀请面试
        jobRelation.setInterviewTime(interviewTime);
        jobRelation.setCreateTime(new Date());

        int count = jobRelationMapper.insertSelective(jobRelation);

        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", "申请面试");
        return result;
    }

    public Map<String, Object> acceptInterview(String id, String status) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(id);
        jobRelation.setAcceptInterview(status);

        int count = jobRelationMapper.updateByPrimaryKeySelective(jobRelation);

        Map<String,Object> result = new HashMap<>();

        if(count > 0){
            result.put("result", true);
            result.put("message", "Y".equals(status) ? "接受面试邀请成功" : "拒绝面试邀请成功");
        }else{
            result.put("result", false);
            result.put("message", "操作失败");
        }
        return result;
    }

    public Map<String, Object> updateInterviewResult(String id, String passFlag) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(id);
        jobRelation.setPassFlag(passFlag);

        int count = jobRelationMapper.updateByPrimaryKeySelective(jobRelation);

        Map<String,Object> result = new HashMap<>();

        if(count > 0){
            result.put("result", true);
            result.put("message", "Y".equals(passFlag) ? "面试通过" : "面试失败");
        }else{
            result.put("result", false);
            result.put("message", "操作失败");
        }
        return result;
    }

    public Map<String, Object> getInterviewResult(String id) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(id);

        Map<String,Object> result = new HashMap<>();

        result.put("result", jobRelation.getPassFlag());
        return result;
    }

    public Map<String, Object> evaluation(String id, String interviewEvaluation) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(id);
        jobRelation.setEvaluationTime(new Date());
        jobRelation.setInterviewEvaluation(interviewEvaluation);

        int count = jobRelationMapper.updateByPrimaryKeySelective(jobRelation);

        Map<String,Object> result = new HashMap<>();

        if(count > 0){
            result.put("result", true);
            result.put("message", "评价成功");
        }else{
            result.put("result", false);
            result.put("message", "操作失败");
        }
        return result;
    }

    public Map<String, Object> listInvitation(SearchVo searchVo) {
        Map<String,Object> resultMap = wrapPage(() -> jobRelationMapper.listInvitation(searchVo.getCondition()),searchVo);
        return resultMap;
    }

    public Map<String, Object> notSee(String jobRelationId) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(jobRelationId);

        jobRelation.setCanInterview("R");//邀请面试

        int count = jobRelationMapper.updateByPrimaryKeySelective(jobRelation);

        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", "不邀请面试");
        return result;
    }

    public Map<String, Object> invitation2(String jobRelationId, String interviewDate) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(jobRelationId);
        jobRelation.setInterviewTime(interviewDate);
        jobRelation.setCanInterview("I");
        int count = jobRelationMapper.updateByPrimaryKeySelective(jobRelation);

        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", "邀请面试成功");
        return result;

    }

    /**
     * 简历匹配以及展示
     * @param searchVo
     * @return
     */
    public Map<String,Object> listJobHunter2(SearchVo searchVo) {
        Map<String,Object> resultMap = wrapPage(() -> jobRelationMapper.listInvitation(searchVo.getCondition()),searchVo);

        List<JobRelationDTO> list = (List<JobRelationDTO>) resultMap.get("queryresult");
        list.stream().forEach(item->{
            JobHunter jobHunter = jobHunterService.selectByPrimaryKey(item.getHunterId().toString());

            String[] arr = item.getEnterpriseTag().split(",");
			if(jobHunter.getTag() == null || "".equals(jobHunter.getTag())){
				item.setRate(0);
			}else{
				List<String> enterpriseTagList = Arrays.asList(arr);
				String[] jobHunterArr = jobHunter.getTag().split(",");
				List<String> jobHunterTagList = Arrays.asList(jobHunterArr);
				
				List<String> intersection = jobHunterTagList.stream().filter(i -> enterpriseTagList.contains(i)).collect(Collectors.toList());
				//简历百分比
				double ratex = (Double.valueOf(intersection.size()) / Double.valueOf(enterpriseTagList.size())) * 100;
				item.setRate(ratex);
			}
			item.setJobHunter(jobHunter);
        });
        resultMap.put("queryresult", list);
        return resultMap;
    }

    public Map<String,Object> cloudSign(String id, String status) {
        JobRelation jobRelation = jobRelationMapper.selectByPrimaryKey(id);
        jobRelation.setSignFlag(status);

        if("3".equals(status)){
            jobRelation.setSignDate(new Date());
        }

        int count = jobRelationMapper.updateByPrimaryKeySelective(jobRelation);

        Map<String,Object> result = new HashMap<>();

        if(count > 0){
            result.put("result", true);
            result.put("message", "云签约操作成功");
        }else{
            result.put("result", false);
            result.put("message", "云签约操作失败");
        }
        return result;
    }
}
