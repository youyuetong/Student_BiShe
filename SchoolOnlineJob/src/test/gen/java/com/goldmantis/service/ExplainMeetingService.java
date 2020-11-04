package edu.job.service;

import edu.job.entity.ExplainMeeting;
import BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.job.mapper.ExplainMeetingMapper;
import org.springframework.stereotype.Service;


/**
 * 的Service
 * @author caoxingxing
 * @date 2020-04-14 21:18:42
 */
@Service
public class ExplainMeetingService extends BaseService<ExplainMeeting> {

    @Autowired
    private ExplainMeetingMapper explainMeetingMapper;


}
