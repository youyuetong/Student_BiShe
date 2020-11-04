package edu.job.dto.job;

import edu.job.entity.JobHunter;

import java.util.Date;

public class JobHunterDTO extends JobHunter {

    private String interviewId;

    private String enterpriseId;

    private String jobRelationId;

    private String canInterview;

    private String interviewTime;

    private String acceptInterview;

    private String passFlag;

    private String interviewEvaluation;

    private Date evaluationTime;

    private String genderDesc;

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getJobRelationId() {
        return jobRelationId;
    }

    public void setJobRelationId(String jobRelationId) {
        this.jobRelationId = jobRelationId;
    }

    public String getCanInterview() {
        return canInterview;
    }

    public void setCanInterview(String canInterview) {
        this.canInterview = canInterview;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getAcceptInterview() {
        return acceptInterview;
    }

    public void setAcceptInterview(String acceptInterview) {
        this.acceptInterview = acceptInterview;
    }

    public String getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
    }

    public String getInterviewEvaluation() {
        return interviewEvaluation;
    }

    public void setInterviewEvaluation(String interviewEvaluation) {
        this.interviewEvaluation = interviewEvaluation;
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getGenderDesc() {
        return genderDesc;
    }

    public void setGenderDesc(String genderDesc) {
        this.genderDesc = genderDesc;
    }
}
