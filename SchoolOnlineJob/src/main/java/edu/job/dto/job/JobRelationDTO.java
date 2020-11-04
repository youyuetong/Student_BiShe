package edu.job.dto.job;

import edu.job.entity.JobHunter;
import edu.job.entity.JobRelation;

import java.math.BigDecimal;

public class JobRelationDTO extends JobRelation {

    private String jobName;

    private String companyName;

    private JobHunter jobHunter;

    private String enterpriseTag;

    private double rate;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public JobHunter getJobHunter() {
        return jobHunter;
    }

    public void setJobHunter(JobHunter jobHunter) {
        this.jobHunter = jobHunter;
    }

    public String getEnterpriseTag() {
        return enterpriseTag;
    }

    public void setEnterpriseTag(String enterpriseTag) {
        this.enterpriseTag = enterpriseTag;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
