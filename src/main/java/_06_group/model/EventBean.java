package _06_group.model;

import java.io.Serializable;
import java.util.List;

import _01_register.model.MemberBean;

public class EventBean implements Serializable {
    private static final long serialVersionUID = 1L;

    int eventID;
    int userID;
    int groupID;
    int kind;
    String title;
    String comment;
    String createTime;
    String updateTime;
    String deadline;
    String place;
    double lat;
    double lng;
    String address;
    int max;
    int bonus;
    String time;
    List<MemberBean> memberBeans;
    
    public EventBean(int eventID, int userID, int groupID, int kind, String title, String comment, String createTime,
	    String updateTime, String deadline, String place, double lat, double lng, String address, int max,
	    int bonus, String time, List<MemberBean> memberBeans) {
	super();
	this.eventID = eventID;
	this.userID = userID;
	this.groupID = groupID;
	this.kind = kind;
	this.title = title;
	this.comment = comment;
	this.createTime = createTime;
	this.updateTime = updateTime;
	this.deadline = deadline;
	this.place = place;
	this.lat = lat;
	this.lng = lng;
	this.address = address;
	this.max = max;
	this.bonus = bonus;
	this.time = time;
	this.memberBeans = memberBeans;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
