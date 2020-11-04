package edu.job.dto.job;

import edu.job.entity.Job;

public class JobDTO extends Job {

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
