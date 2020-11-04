package edu.job.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 简历回馈
 * @author null
 * @date 2019-05-16 20:38:42
 */
@Table(name = "job_relation")
public class JobRelation {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 职位Id
     */
    @Column(name = "job_id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long jobId;

    /**
     * 求职人Id
     */
    @Column(name = "hunter_id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long hunterId;

    /**
     * 面试官ID
     */
    @Column(name = "interview_id")
    private Long interviewId;

    /**
     * 申请时间
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 是否邀请面试
     */
    @Column(name = "can_interview")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String canInterview;

    /**
     * 邀请面试时间
     */
    @Column(name = "interview_time")
    private String interviewTime;

    /**
     * 是否接收面试
     */
    @Column(name = "accept_interview")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String acceptInterview;

    /**
     * 是否通过面试
     */
    @Column(name = "pass_flag")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String passFlag;

    /**
     * 面试评价
     */
    @Column(name = "interview_evaluation")
    @Size(min = 0, max = 100 , message = "长度必须在{min}和{max}之间")
    private String interviewEvaluation;

    /**
     * 评价时间
     */
    @Column(name = "evaluation_time")
    private Date evaluationTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "sign_flag")
    private String signFlag;

    @Column(name = "sign_pic_url")
    private String signPicUrl;

    @Column(name = "sign_date")
    private Date signDate;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取职位Id
     *
     * @return job_id - 职位Id
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 设置职位Id
     *
     * @param jobId 职位Id
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取求职人Id
     *
     * @return hunter_id - 求职人Id
     */
    public Long getHunterId() {
        return hunterId;
    }

    /**
     * 设置求职人Id
     *
     * @param hunterId 求职人Id
     */
    public void setHunterId(Long hunterId) {
        this.hunterId = hunterId;
    }

    /**
     * 获取面试官ID
     *
     * @return interview_id - 面试官ID
     */
    public Long getInterviewId() {
        return interviewId;
    }

    /**
     * 设置面试官ID
     *
     * @param interviewId 面试官ID
     */
    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    /**
     * 获取是否邀请面试
     *
     * @return can_interview - 是否邀请面试
     */
    public String getCanInterview() {
        return canInterview;
    }

    /**
     * 设置是否邀请面试
     *
     * @param canInterview 是否邀请面试
     */
    public void setCanInterview(String canInterview) {
        this.canInterview = canInterview == null ? null : canInterview.trim();
    }

    /**
     * 获取邀请面试时间
     *
     * @return interview_time - 邀请面试时间
     */
    public String getInterviewTime() {
        return interviewTime;
    }

    /**
     * 设置邀请面试时间
     *
     * @param interviewTime 邀请面试时间
     */
    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    /**
     * 获取是否接收面试
     *
     * @return accept_interview - 是否接收面试
     */
    public String getAcceptInterview() {
        return acceptInterview;
    }

    /**
     * 设置是否接收面试
     *
     * @param acceptInterview 是否接收面试
     */
    public void setAcceptInterview(String acceptInterview) {
        this.acceptInterview = acceptInterview == null ? null : acceptInterview.trim();
    }

    /**
     * 获取是否通过面试
     *
     * @return pass_flag - 是否通过面试
     */
    public String getPassFlag() {
        return passFlag;
    }

    /**
     * 设置是否通过面试
     *
     * @param passFlag 是否通过面试
     */
    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag == null ? null : passFlag.trim();
    }

    /**
     * 获取面试评价
     *
     * @return interview_evaluation - 面试评价
     */
    public String getInterviewEvaluation() {
        return interviewEvaluation;
    }

    /**
     * 设置面试评价
     *
     * @param interviewEvaluation 面试评价
     */
    public void setInterviewEvaluation(String interviewEvaluation) {
        this.interviewEvaluation = interviewEvaluation == null ? null : interviewEvaluation.trim();
    }

    /**
     * 获取评价时间
     *
     * @return evaluation_time - 评价时间
     */
    public Date getEvaluationTime() {
        return evaluationTime;
    }

    /**
     * 设置评价时间
     *
     * @param evaluationTime 评价时间
     */
    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", jobId=").append(jobId);
        sb.append(", hunterId=").append(hunterId);
        sb.append(", interviewId=").append(interviewId);
        sb.append(", canInterview=").append(canInterview);
        sb.append(", interviewTime=").append(interviewTime);
        sb.append(", acceptInterview=").append(acceptInterview);
        sb.append(", passFlag=").append(passFlag);
        sb.append(", interviewEvaluation=").append(interviewEvaluation);
        sb.append(", evaluationTime=").append(evaluationTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }

    public String getSignPicUrl() {
        return signPicUrl;
    }

    public void setSignPicUrl(String signPicUrl) {
        this.signPicUrl = signPicUrl;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}