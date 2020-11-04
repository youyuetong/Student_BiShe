package edu.job.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 职位介绍
 * @author null
 * @date 2019-05-16 20:36:56
 */
@Table(name = "job")
public class Job {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    @Column(name="enterprise_id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long enterpriseId;

    /**
     * 职位名称
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String name;

    /**
     * 要求工作年限开始
     */
    @Column(name = "experience_start")
    @Max(value=2147483647,message="最大值不能高于{value}")
    @Min(value=-2147483648,message="最小值不能低于{value}")
    private Integer experienceStart;

    @Column(name = "experience_end")
    @Max(value=2147483647,message="最大值不能高于{value}")
    @Min(value=-2147483648,message="最小值不能低于{value}")
    private Integer experienceEnd;

    /**
     * 学历最低水平
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String education;

    /**
     * 职位标签
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String tag;

    /**
     * 职位诱惑
     */
    @Size(min = 0, max = 200 , message = "长度必须在{min}和{max}之间")
    private String temptation;

    /**
     * 职位描述
     */
    @Size(min = 0, max = 1000 , message = "长度必须在{min}和{max}之间")
    private String introduction;

    /**
     * 发布时间
     */
    @Column(name = "published_time")
    private Date publishedTime;

    /**
     * 发布人
     */
    @Column(name = "published_by")
    @Size(min = 0, max = 255 , message = "长度必须在{min}和{max}之间")
    private String publishedBy;

    @Column(name = "published_id")
    private Long publishedId;

    @Column(name = "active_flg")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String activeFlg;

    @Column(name = "pay_start")
    private double payStart;

    @Column(name = "pay_end")
    private double payEnd;

    @Column(name = "work_address")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String workAddress;

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
     * 获取职位名称
     *
     * @return name - 职位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置职位名称
     *
     * @param name 职位名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取要求工作年限开始
     *
     * @return experience_start - 要求工作年限开始
     */
    public Integer getExperienceStart() {
        return experienceStart;
    }

    /**
     * 设置要求工作年限开始
     *
     * @param experienceStart 要求工作年限开始
     */
    public void setExperienceStart(Integer experienceStart) {
        this.experienceStart = experienceStart;
    }

    /**
     * @return experience_end
     */
    public Integer getExperienceEnd() {
        return experienceEnd;
    }

    /**
     * @param experienceEnd
     */
    public void setExperienceEnd(Integer experienceEnd) {
        this.experienceEnd = experienceEnd;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取职位标签
     *
     * @return tag - 职位标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置职位标签
     *
     * @param tag 职位标签
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * 获取职位诱惑
     *
     * @return temptation - 职位诱惑
     */
    public String getTemptation() {
        return temptation;
    }

    /**
     * 设置职位诱惑
     *
     * @param temptation 职位诱惑
     */
    public void setTemptation(String temptation) {
        this.temptation = temptation == null ? null : temptation.trim();
    }

    /**
     * 获取职位描述
     *
     * @return introduction - 职位描述
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置职位描述
     *
     * @param introduction 职位描述
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 获取发布时间
     *
     * @return published_time - 发布时间
     */
    public Date getPublishedTime() {
        return publishedTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishedTime 发布时间
     */
    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    /**
     * 获取发布人
     *
     * @return published_by - 发布人
     */
    public String getPublishedBy() {
        return publishedBy;
    }

    /**
     * 设置发布人
     *
     * @param publishedBy 发布人
     */
    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy == null ? null : publishedBy.trim();
    }

    /**
     * @return published_id
     */
    public Long getPublishedId() {
        return publishedId;
    }

    /**
     * @param publishedId
     */
    public void setPublishedId(Long publishedId) {
        this.publishedId = publishedId;
    }

    /**
     * @return active_flg
     */
    public String getActiveFlg() {
        return activeFlg;
    }

    /**
     * @param activeFlg
     */
    public void setActiveFlg(String activeFlg) {
        this.activeFlg = activeFlg == null ? null : activeFlg.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", experienceStart=").append(experienceStart);
        sb.append(", experienceEnd=").append(experienceEnd);
        sb.append(", eduction=").append(education);
        sb.append(", tag=").append(tag);
        sb.append(", temptation=").append(temptation);
        sb.append(", introduction=").append(introduction);
        sb.append(", publishedTime=").append(publishedTime);
        sb.append(", publishedBy=").append(publishedBy);
        sb.append(", publishedId=").append(publishedId);
        sb.append(", activeFlg=").append(activeFlg);
        sb.append("]");
        return sb.toString();
    }

    public double getPayStart() {
        return payStart;
    }

    public void setPayStart(double payStart) {
        this.payStart = payStart;
    }

    public double getPayEnd() {
        return payEnd;
    }

    public void setPayEnd(double payEnd) {
        this.payEnd = payEnd;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
}