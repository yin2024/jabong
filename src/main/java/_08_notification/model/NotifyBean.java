package _08_notification.model;

import java.io.Serializable;

public class NotifyBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // 儲存通知用的類別
    int notifyID;	// 通知流水號
    int userID;		// 用戶帳號
    int sID;		// 主用戶ID 
    int oID;		// 受用戶ID
    String name;		// 主姓名
    int type;		// 通知類型，1，交友申請，2，點讚通知，3，推文通知
    int typeID; 		// 該類型表單的流水號，如type=1，則typeID=friendID
    int check;		// 0，未讀通知。 1，已讀通知。
    String time;		// 通知時間點
    
    public NotifyBean() {	
    }

    public NotifyBean(int notifyID, int userID, int sID, int oID, String name, int type, int typeID, int check,
	    String time) {
	super();
	this.notifyID = notifyID;
	this.userID = userID;
	this.sID = sID;
	this.oID = oID;
	this.name = name;
	this.type = type;
	this.typeID = typeID;
	this.check = check;
	this.time = time;
    }

    public int getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(int notifyID) {
        this.notifyID = notifyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}