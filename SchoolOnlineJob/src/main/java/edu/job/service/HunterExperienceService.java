package edu.job.service;

import edu.job.base.BaseService;
import edu.job.entity.HunterExperience;
import edu.job.mapper.HunterExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HunterExperienceService extends BaseService<HunterExperience> {

    @Autowired
    private HunterExperienceMapper hunterExperienceMapper;

    public List<HunterExperience> getHunterExperienceByHunterIdAndType(Long hunterId, String type){
        Map<String,Object> param = new HashMap();
        param.put("hunterId", hunterId);
        param.put("experienceType", type);

        List<HunterExperience> experienceList = hunterExperienceMapper.selectByCondition(param);
        return experienceList;
    }

    public void deleteByHunterId(Long id) {
        hunterExperienceMapper.deleteByHunterId(id);
    }
}
