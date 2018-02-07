package _04_friend.model;

import java.io.Serializable;
import java.util.List;

import _03_post.model.*;

public class FriendBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // friend表格的流水號
    int friendID;
    // 以下為定義某一個friend的基本資料
    int userID;			// 朋友的用戶編號
    String account;		// 朋友的帳號
    String name;			// 朋友的姓名
    String comment;		// 朋友的心情
    int mutual; 			// 共同好友數量
    int status;			// 與好友的狀態
    String lastPostPlace;	// 朋友最新的打卡地點
    String lastPostTime;		// 朋友最新的打卡時間
    int point;
    String email;
    String tel;
    String birth;
    String login;
    String create;
    List<PostBean>  postBeans;  // 朋友發過的所有文章
    String postJson;
    
    public FriendBean() {
    }

    public FriendBean(int friendID, int userID, String account, String name, String comment, int mutual, int status,
	    String lastPostPlace, String lastPostTime, int point, String email, String tel, String birth, String login,
	    String create, List<PostBean> postBeans, String postJson) {
	super();
	this.friendID = friendID;
	this.userID = userID;
	this.account = account;
	this.name = name;
	this.comment = comment;
	this.mutual = mutual;
	this.status = status;
	this.lastPostPlace = lastPostPlace;
	this.lastPostTime = lastPostTime;
	this.point = point;
	this.email = email;
	this.tel = tel;
	this.birth = birth;
	this.login = login;
	this.create = create;
	this.postBeans = postBeans;
	this.postJson = postJson;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMutual() {
        return mutual;
    }

    public void setMutual(int mutual) {
        this.mutual = mutual;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLastPostPlace() {
        return lastPostPlace;
    }

    public void setLastPostPlace(String lastPostPlace) {
        this.lastPostPlace = lastPostPlace;
    }

    public String getLastPostTime() {
        return lastPostTime;
    }

    public void setLastPostTime(String lastPostTime) {
        this.lastPostTime = lastPostTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public List<PostBean> getPostBeans() {
        return postBeans;
    }

    public void setPostBeans(List<PostBean> postBeans) {
        this.postBeans = postBeans;
    }

    public String getPostJson() {
        return postJson;
    }

    public void setPostJson(String postJson) {
        this.postJson = postJson;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
    