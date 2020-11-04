package edu.job.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 宣讲会
 */
@Table(name = "explain_meeting")
public class ExplainMeeting {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long enterpriseId;

    /**
     * 宣讲会标题
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String title;

    /**
     * 宣讲会简介
     */
    @Size(min = 0, max = 255 , message = "长度必须在{min}和{max}之间")
    private String introduction;

    /**
     * 宣传海报图片地址
     */
    @Column(name = "poster_url")
    @Size(min = 0, max = 255 , message = "长度必须在{min}和{max}之间")
    private String posterUrl;

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
     * 消息发布时间
     */
    @Column(name = "publish_date")
    private Date publishDate;

    /**
     * 进入宣讲二维码地址
     */
    @Column(name = "qr_code")
    @Size(min = 0, max = 255 , message = "长度必须在{min}和{max}之间")
    private String qrCode;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String createUser;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_user")
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String modifyUser;

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
     * 获取企业ID
     *
     * @return enterprise_id - 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置企业ID
     *
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取宣讲会标题
     *
     * @return title - 宣讲会标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置宣讲会标题
     *
     * @param title 宣讲会标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取宣讲会简介
     *
     * @return introduction - 宣讲会简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置宣讲会简介
     *
     * @param introduction 宣讲会简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 获取宣传海报图片地址
     *
     * @return poster_url - 宣传海报图片地址
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * 设置宣传海报图片地址
     *
     * @param posterUrl 宣传海报图片地址
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl == null ? null : posterUrl.trim();
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

    /**
     * 获取消息发布时间
     *
     * @return publish_date - 消息发布时间
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * 设置消息发布时间
     *
     * @param publishDate 消息发布时间
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 获取进入宣讲二维码地址
     *
     * @return qr_code - 进入宣讲二维码地址
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * 设置进入宣讲二维码地址
     *
     * @param qrCode 进入宣讲二维码地址
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取修改人
     *
     * @return modify_user - 修改人
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * 设置修改人
     *
     * @param modifyUser 修改人
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", title=").append(title);
        sb.append(", introduction=").append(introduction);
        sb.append(", posterUrl=").append(posterUrl);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append("]");
        return sb.toString();
    }
}