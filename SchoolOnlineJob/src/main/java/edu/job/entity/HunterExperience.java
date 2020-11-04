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
 * 简历-经历内容
 * @author null
 * @date 2020-05-05 16:47:42
 */
@Table(name = "hunter_experience")
public class HunterExperience {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 经历类型
     */
    @Column(name = "experience_type")
    @JSONField(serializeUsing = ToStringSerializer.class)
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String experienceType;

    @Column(name = "hunter_id")
    private Long hunterId;

    /**
     * 经历内容
     */
    @Column(name = "experience_content")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String experienceContent;

    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

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
     * 获取经历类型
     *
     * @return experience_type - 经历类型
     */
    public String getExperienceType() {
        return experienceType;
    }

    /**
     * 设置经历类型
     *
     * @param experienceType 经历类型
     */
    public void setExperienceType(String experienceType) {
        this.experienceType = experienceType == null ? null : experienceType.trim();
    }

    /**
     * @return hunter_id
     */
    public Long getHunterId() {
        return hunterId;
    }

    /**
     * @param hunterId
     */
    public void setHunterId(Long hunterId) {
        this.hunterId = hunterId;
    }

    /**
     * 获取经历内容
     *
     * @return experience_content - 经历内容
     */
    public String getExperienceContent() {
        return experienceContent;
    }

    /**
     * 设置经历内容
     *
     * @param experienceContent 经历内容
     */
    public void setExperienceContent(String experienceContent) {
        this.experienceContent = experienceContent == null ? null : experienceContent.trim();
    }

    /**
     * 获取开始时间
     *
     * @return start_date - 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始时间
     *
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", experienceType=").append(experienceType);
        sb.append(", hunterId=").append(hunterId);
        sb.append(", experienceContent=").append(experienceContent);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append("]");
        return sb.toString();
    }
}