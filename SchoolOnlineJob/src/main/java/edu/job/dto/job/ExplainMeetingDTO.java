package edu.job.dto.job;

import edu.job.entity.EnterpriseInfo;
import edu.job.entity.ExplainMeeting;

public class ExplainMeetingDTO extends ExplainMeeting {

    private String enterpriseName;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
