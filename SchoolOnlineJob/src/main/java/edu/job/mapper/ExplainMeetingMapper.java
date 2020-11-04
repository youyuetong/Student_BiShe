package edu.job.mapper;


import edu.job.base.BaseMapper;
import edu.job.entity.ExplainMeeting;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExplainMeetingMapper extends BaseMapper<ExplainMeeting> {
    List<ExplainMeeting> doquery(Map<String, Object> condition);
}