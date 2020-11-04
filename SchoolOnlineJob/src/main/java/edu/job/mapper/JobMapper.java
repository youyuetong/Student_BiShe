package edu.job.mapper;

import edu.job.base.BaseMapper;
import edu.job.dto.job.JobDTO;
import edu.job.entity.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JobMapper extends BaseMapper<Job> {
    List<JobDTO> doquery(Map<String, Object> condition);

    List<Job> selectByEnterpriseId(@Param(value = "id") String id);
}