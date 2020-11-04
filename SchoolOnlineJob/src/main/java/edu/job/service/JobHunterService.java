package edu.job.service;

import com.alibaba.fastjson.JSONObject;
import edu.job.base.BaseService;
import edu.job.dto.SearchVo;
import edu.job.entity.JobHunter;
import edu.job.entity.User;
import edu.job.enumeration.UserEnum;
import edu.job.mapper.JobHunterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 求职者Service
 */
@Service
public class JobHunterService extends BaseService<JobHunter> {

    @Autowired
    private UserService userService;

    @Autowired
    private JobHunterMapper jobHunterMapper;


    public JobHunter selectByUserId(String userId) {
        return jobHunterMapper.selectByUserId(userId);
    }

    public Map<String, Object> doquery(SearchVo searchVo) {
        searchVo.addCondition("canSearch","Y");
        String userId = (String) searchVo.getCondition().get("userId");
        if(!StringUtils.isEmpty(userId)){
            User user = userService.selectByPrimaryKey(userId);
            if(user.getUserType().equals(UserEnum.USER_TYPE_ADMIN.getCode())){
                searchVo.addCondition("canSearch","");
            }
        }

        Map<String,Object> resultMap = wrapPage(() -> jobHunterMapper.doquery(searchVo.getCondition()),searchVo);
        return resultMap;
    }

    public List<JobHunter> listJobHunter(String jobId, JSONObject jsonObject) {
        return jobHunterMapper.listJobHunter(jobId, jsonObject);
    }

    public Map<String, Object> getHunterListByJobId(SearchVo searchVo) {
        Map<String,Object> resultMap = wrapPage(() -> jobHunterMapper.getHunterListByJobId(searchVo.getCondition()),searchVo);
        return resultMap;
    }
}
