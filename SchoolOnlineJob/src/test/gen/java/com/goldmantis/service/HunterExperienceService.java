package edu.job.service;

import edu.job.entity.HunterExperience;
import BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.job.mappers.HunterExperienceMapper;
import org.springframework.stereotype.Service;


/**
 * 的Service
 * @author caoxingxing
 * @date 2020-05-05 16:47:43
 */
@Service
public class HunterExperienceService extends BaseService<HunterExperience> {

    @Autowired
    private HunterExperienceMapper hunterExperienceMapper;


}
