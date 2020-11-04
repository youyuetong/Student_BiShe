package edu.job.mapper;

import edu.job.base.BaseMapper;
import edu.job.dto.job.JobRelationDTO;
import edu.job.entity.JobRelation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JobRelationMapper extends BaseMapper<JobRelation> {
    List<JobRelationDTO> listInvitation(Map<String, Object> condition);
}