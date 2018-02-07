package _03_post.model;

import java.io.Serializable;
import java.sql.Blob;

public class PostRespBean implements Serializable {
    private static final long serialVersionUID = 1L;

    int postRespID;
    int postID;
    int userID;
    String userName;
    String response;
    String time;

    public PostRespBean() {
    }

    public PostRespBean(int postRespID, int postID, int userID, String userName, String response, String time) {
	super();
	this.postRespID = postRespID;
	this.postID = postID;
	this.userID = userID;
	this.userName = userName;
	this.response = response;
	this.time = time;
    }

    public int getPostRespID() {
        return postRespID;
    }

    public void setPostRespID(int postRespID) {
        this.postRespID = postRespID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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