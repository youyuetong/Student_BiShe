package edu.job.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author null
 * @date 2020-04-14 21:15:13
 */
@Table(name = "chat")
public class Chat {
    @Id
    @NotNull
    private Long id;

    /**
     * 发给某人
     */
    @Column(name = "to_user_id")
    private Long toUserId;

    /**
     * 聊天内容
     */
    @Column(name = "chat_content")
    @Size(min = 0, max = 255 , message = "长度必须在{min}和{max}之间")
    private String chatContent;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 聊天类型（1-用户，2-医生）
     */
    @Column(name = "chat_type")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String chatType;

    /**
     * 聊天类型（文字-txt，图片-img）
     */
    @Column(name = "chat_format")
    @Size(min = 0, max = 5 , message = "长度必须在{min}和{max}之间")
    private String chatFormat;

    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 是否已读（1-已读，0-未读）
     */
    @Column(name = "read_flag")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String readFlag;

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
     * 获取发给某人
     *
     * @return to_user_id - 发给某人
     */
    public Long getToUserId() {
        return toUserId;
    }

    /**
     * 设置发给某人
     *
     * @param toUserId 发给某人
     */
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取聊天内容
     *
     * @return chat_content - 聊天内容
     */
    public String getChatContent() {
        return chatContent;
    }

    /**
     * 设置聊天内容
     *
     * @param chatContent 聊天内容
     */
    public void setChatContent(String chatContent) {
        this.chatContent = chatContent == null ? null : chatContent.trim();
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
     * 获取聊天类型（1-用户，2-医生）
     *
     * @return chat_type - 聊天类型（1-用户，2-医生）
     */
    public String getChatType() {
        return chatType;
    }

    /**
     * 设置聊天类型（1-用户，2-医生）
     *
     * @param chatType 聊天类型（1-用户，2-医生）
     */
    public void setChatType(String chatType) {
        this.chatType = chatType == null ? null : chatType.trim();
    }

    /**
     * 获取聊天类型（文字-txt，图片-img）
     *
     * @return chat_format - 聊天类型（文字-txt，图片-img）
     */
    public String getChatFormat() {
        return chatFormat;
    }

    /**
     * 设置聊天类型（文字-txt，图片-img）
     *
     * @param chatFormat 聊天类型（文字-txt，图片-img）
     */
    public void setChatFormat(String chatFormat) {
        this.chatFormat = chatFormat == null ? null : chatFormat.trim();
    }

    /**
     * @return create_user_id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取是否已读（1-已读，0-未读）
     *
     * @return read_flag - 是否已读（1-已读，0-未读）
     */
    public String getReadFlag() {
        return readFlag;
    }

    /**
     * 设置是否已读（1-已读，0-未读）
     *
     * @param readFlag 是否已读（1-已读，0-未读）
     */
    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag == null ? null : readFlag.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", chatContent=").append(chatContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", chatType=").append(chatType);
        sb.append(", chatFormat=").append(chatFormat);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", readFlag=").append(readFlag);
        sb.append("]");
        return sb.toString();
    }
}