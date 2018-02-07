package _06_group.model;

import java.io.Serializable;
import java.util.List;

import _01_register.model.MemberBean;

public class GroupBean implements Serializable {
    private static final long serialVersionUID = 1L;

    int groupID;
    String name;
    int kind;
    int userID;
    String comment;
    String location;

    List<EventBean> eventBeans;
    List<MemberBean> memberBeans;

    public GroupBean() {

    }

    public GroupBean(int groupID, String name, int kind, int userID, String comment, String location,
	    List<EventBean> eventBeans, List<MemberBean> memberBeans) {
	super();
	this.groupID = groupID;
	this.name = name;
	this.kind = kind;
	this.userID = userID;
	this.comment = comment;
	this.location = location;
	this.eventBeans = eventBeans;
	this.memberBeans = memberBeans;
    }

    public int getGroupID() {
	return groupID;
    }

    public void setGroupID(int groupID) {
	this.groupID = groupID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getKind() {
	return kind;
    }

    public void setKind(int kind) {
	this.kind = kind;
    }

    public int getUserID() {
	return userID;
    }

    public void setUserID(int userID) {
	this.userID = userID;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public List<EventBean> getEventBeans() {
	return eventBeans;
    }

    public void setEventBeans(List<EventBean> eventBeans) {
	this.eventBeans = eventBeans;
    }

    public List<MemberBean> getMemberBeans() {
	return memberBeans;
    }

    public void setMemberBeans(List<MemberBean> memberBeans) {
	this.memberBeans = memberBeans;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}