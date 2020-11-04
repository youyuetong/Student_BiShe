package edu.job.service;

import edu.job.base.BaseService;
import edu.job.dto.CodeDescVo;
import edu.job.dto.SearchVo;
import edu.job.dto.job.JobDTO;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.Job;
import edu.job.entity.User;
import edu.job.enumeration.UserEnum;
import edu.job.mapper.JobMapper;
import edu.job.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * 职位Service
 */
@Service
public class JobService extends BaseService<Job> {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    @Autowired
    private UserService userService;

    public Map<String, Object> save(Job job, User user) {
        Long id = user.getId();
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(id.toString());
        Long enterpriseId = enterpriseInfo.getId();

        job.setEnterpriseId(enterpriseId);
        job.setActiveFlg("1");//待发布
        job.setPublishedId(user.getId());
        job.setPublishedBy(user.getLoginName());

        int count = 0;
        if(StringUtils.isEmpty(job.getId())){
            job.setId(IDGenerator.generateId());
            count = jobMapper.insert(job);
        }else{
            count = jobMapper.updateByPrimaryKeySelective(job);
        }

        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", count > 0 ? "保存成功" : "保存失败");
        return result;
    }

    public Map<String, Object> publish(String id) {
        Job job = jobMapper.selectByPrimaryKey(id);
        job.setActiveFlg("2");
        job.setPublishedTime(new Date());

        int count = jobMapper.updateByPrimaryKeySelective(job);

        Map<String,Object> result = new HashMap<>();

        boolean flag = count > 0;
        result.put("result", flag);
        result.put("message", flag ? "发布成功" : "发布失败");
        return result;
    }

    public Map<String, Object> unpublish(String id) {
        Job job = jobMapper.selectByPrimaryKey(id);
        job.setActiveFlg("1");
        job.setPublishedTime(null);

        int count = jobMapper.updateByPrimaryKeySelective(job);

        Map<String,Object> result = new HashMap<>();

        boolean flag = count > 0;
        result.put("result", flag);
        result.put("message", flag ? "发布成功" : "发布失败");
        return result;
    }

    public Map<String, Object> doquery(SearchVo searchVo, User user) {
        if(user.getUserType().equals(UserEnum.USER_TYPE_ENTERPRISE.getCode())){
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
            if(enterpriseInfo != null){
                searchVo.addCondition("enterpriseId", enterpriseInfo.getId());
            }
        }else if(user.getUserType().equals(UserEnum.USER_TYPE_JOB_HUNTER.getCode())){
            //求职者只能看到审核通过的
            searchVo.addCondition("enterpriseStatus", "审核通过");
        }
        Map<String,Object> resultMap = wrapPage(() -> jobMapper.doquery(searchVo.getCondition()),searchVo);
        return resultMap;
    }

    public Map<String, Object> delete(String id) {
        int count = jobMapper.deleteByPrimaryKey(id);

        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", count > 0 ? "删除成功":"删除失败");
        return result;
    }

    public Map<String, Object> view(String id) {
        Job job = jobMapper.selectByPrimaryKey(id);
        Map<String,Object> result = new HashMap<>();
        result.put("result", job);
        return result;
    }

    public Map<String, Object> viewNew(String userId) {
//        User user = userService.selectByPrimaryKey(userId);
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(userId);

        Job job = new Job();
        job.setEnterpriseId(enterpriseInfo.getId());

        Map<String,Object> result = new HashMap<>();
        result.put("result", job);
        return result;
    }

    public Map<String, Object> deleteJob(String id) {

        int count = jobMapper.deleteByPrimaryKey(id);
        Map<String,Object> result = new HashMap<>();
        result.put("result", count > 0);
        result.put("message", "删除成功");
        return result;
    }

    public Map<String, Object> getList(String userId) {
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(userId);

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseInfo.getId());
        map.put("activeFlag", 2);
        List<JobDTO> list = jobMapper.doquery(map);

        List<CodeDescVo> codeDescList = new ArrayList<>();
        list.stream().forEach(item->{
            codeDescList.add(new CodeDescVo(item.getId().toString(),item.getName()));
        });

        Map<String,Object> result = new HashMap<>();
        result.put("jobList", codeDescList);
        return result;
    }

    public Map<String, Object> view2(String id) {
        Job job = jobMapper.selectByPrimaryKey(id);
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(job.getPublishedId().toString());

        User user = userService.selectByPrimaryKey(enterpriseInfo.getUserId());

        Map<String,Object> map = new HashMap<>();
        map.put("job", job);
        map.put("enterpriseInfo", enterpriseInfo);
        map.put("hr", user);
        return map;
    }

    public Map<String,Object> listAll(String userId) {
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(userId);

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseInfo.getId());
//        map.put("activeFlag", 2);
        List<JobDTO> list = jobMapper.doquery(map);

        Map<String,Object> result = new HashMap<>();
        result.put("jobList", list);
        result.put("result", true);
        return result;
    }

    public List<Job> selectByEnterpriseId(String id) {
        return jobMapper.selectByEnterpriseId(id);
    }
}
