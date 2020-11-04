package edu.job.mapper;

import edu.job.base.BaseMapper;
import edu.job.entity.HunterExperience;

import java.util.List;
import java.util.Map;

public interface HunterExperienceMapper extends BaseMapper<HunterExperience> {

    List<HunterExperience> selectByCondition(Map<String,Object> condition);

    void deleteByHunterId(Long id);
}