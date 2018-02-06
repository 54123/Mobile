package com.csc.mobile.entity;

import java.util.List;

/**
 * 单例模式实体类
 * Created by 随风 on 2018/1/30.
 */

public class BasicDateEntity {
    private  static BasicDateEntity basicData;
    public static BasicDateEntity getSingle() {
        if(basicData == null) {
            synchronized (BasicDateEntity.class) {
                if(basicData == null) {
                    basicData = new BasicDateEntity();
                }
            }
        }
        return basicData;
    }
    List<FeedbackContentEntity> liststring;//反馈信息集合
    List<OrganizationEntity> organizationlist;//组织架构集合
    List<DepartmentStaffEntity> departmentStafflist;//部门人员集合
    List<SearchEntity> searchlist;//搜索的内容集合
    List<PersonalInformationEntity> personalInformationList;//个人信息集合
    List<CollectionEntity> collectionnamelist;//收藏信息集合
    List<MineGroupEntity> mineGrouplist;//我的群组集合
    List<GroupSonEntity> groupSonlist;//我的群组子集合
    List<OrganizationEntity> departmentlist;//部门列表
    List<SearchEntity> personList;//人员列表
    List<MessageEntity> messageList;//系统消息列表
    List<GestrueEntity> gestruelists;//手势密码
    UserConfigureEntity configureEntity;//用户配置信息


    public UserConfigureEntity getConfigureEntity() {
        return configureEntity;
    }
    public void setConfigureEntity(UserConfigureEntity configureEntity) {
        this.configureEntity = configureEntity;
    }
    public List<FeedbackContentEntity> getListstring() {
        return liststring;
    }
    public void setListstring(List<FeedbackContentEntity> liststring) {
        this.liststring = liststring;
    }
    public List<OrganizationEntity> getOrganizationlist() {
        return organizationlist;
    }
    public void setOrganizationlist(List<OrganizationEntity> organizationlist) {
        this.organizationlist = organizationlist;
    }
    public List<DepartmentStaffEntity> getDepartmentStafflist() {
        return departmentStafflist;
    }
    public void setDepartmentStafflist(List<DepartmentStaffEntity> departmentStafflist) {
        this.departmentStafflist = departmentStafflist;
    }
    public List<SearchEntity> getSearchlist() {
        return searchlist;
    }
    public void setSearchlist(List<SearchEntity> searchlist) {
        this.searchlist = searchlist;
    }
    public List<PersonalInformationEntity> getPersonalInformationList() {
        return personalInformationList;
    }
    public void setPersonalInformationList(
            List<PersonalInformationEntity> personalInformationList) {
        this.personalInformationList = personalInformationList;
    }
    public List<CollectionEntity> getCollectionnamelist() {
        return collectionnamelist;
    }
    public void setCollectionnamelist(List<CollectionEntity> collectionnamelist) {
        this.collectionnamelist = collectionnamelist;
    }
    public List<MineGroupEntity> getMineGrouplist() {
        return mineGrouplist;
    }
    public void setMineGrouplist(List<MineGroupEntity> mineGrouplist) {
        this.mineGrouplist = mineGrouplist;
    }
    public List<GroupSonEntity> getGroupSonlist() {
        return groupSonlist;
    }
    public void setGroupSonlist(List<GroupSonEntity> groupSonlist) {
        this.groupSonlist = groupSonlist;
    }
    public List<OrganizationEntity> getDepartmentlist() {
        return departmentlist;
    }
    public void setDepartmentlist(List<OrganizationEntity> departmentlist) {
        this.departmentlist = departmentlist;
    }
    public List<SearchEntity> getPersonList() {
        return personList;
    }
    public void setPersonList(List<SearchEntity> personList) {
        this.personList = personList;
    }
    public List<MessageEntity> getMessageList() {
        return messageList;
    }
    public void setMessageList(List<MessageEntity> messageList) {
        this.messageList = messageList;
    }
    public List<GestrueEntity> getGestruelists() {
        return gestruelists;
    }
    public void setGestruelists(List<GestrueEntity> gestruelists) {
        this.gestruelists = gestruelists;
    }

}
