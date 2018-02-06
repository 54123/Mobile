package com.csc.mobile.entity;

/**
 * 个人配置信息实体类 0-否，1-是
 * Created by 随风 on 2018/1/30.
 */

public class UserConfigureEntity {
    private String userId;
    private String apkCache;//应用缓存
    private String phoneCache;//通讯录缓存
    private String getMessage;//定时收取消息
    private String messageTime;//相隔多长时间
    private String cacheTime;//通讯录更新时间
    private String cache;//本地是否有通讯录缓存

    public UserConfigureEntity(String userId,String apkCache,String phoneCache,String getMessage,String messageTime,String cacheTime,String cache){
        this.userId = userId;
        this.apkCache = apkCache;
        this.phoneCache = phoneCache;
        this.getMessage = getMessage;
        this.messageTime = messageTime;
        this.cache = cache;
        this.cacheTime = cacheTime;
    }

    public String getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(String cacheTime) {
        this.cacheTime = cacheTime;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getMessageTime() {
        return messageTime;
    }
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getApkCache() {
        return apkCache;
    }
    public void setApkCache(String apkCache) {
        this.apkCache = apkCache;
    }
    public String getPhoneCache() {
        return phoneCache;
    }
    public void setPhoneCache(String phoneCache) {
        this.phoneCache = phoneCache;
    }
    public String getGetMessage() {
        return getMessage;
    }
    public void setGetMessage(String getMessage) {
        this.getMessage = getMessage;
    }

    @Override
    public String toString() {
        return "UserConfigureEntity [userId=" + userId + ", apkCache="
                + apkCache + ", phoneCache=" + phoneCache + ", getMessage="
                + getMessage + ", messageTime=" + messageTime + ", cacheTime="
                + cacheTime + ", cache=" + cache + "]";
    }
}
