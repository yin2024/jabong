package _03_post.model;

import java.io.Serializable;

public class PostLikeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    int postLikeID;
    int postID;
    int userID;
    String userName;
    int like;
    String time;

    public PostLikeBean() {
    }

    public PostLikeBean(int postLikeID, int postID, int userID, String userName, int like, String time) {
	super();
	this.postLikeID = postLikeID;
	this.postID = postID;
	this.userID = userID;
	this.userName = userName;
	this.like = like;
	this.time = time;
    }

    public int getPostLikeID() {
        return postLikeID;
    }

    public void setPostLikeID(int postLikeID) {
        this.postLikeID = postLikeID;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
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