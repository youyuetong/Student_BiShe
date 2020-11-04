package edu.job.mapper;

import com.alibaba.fastjson.JSONObject;
import edu.job.base.BaseMapper;
import edu.job.dto.job.JobHunterDTO;
import edu.job.entity.JobHunter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JobHunterMapper extends BaseMapper<JobHunter> {
    JobHunter selectByUserId(@Param(value = "userId") String userId);

    List<JobHunter> doquery(Map<String, Object> condition);

    List<JobHunter> listJobHunter(String jobId, JSONObject jsonObject);

    List<JobHunterDTO> getHunterListByJobId(Map<String, Object> condition);
}