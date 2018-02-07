package _03_post.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

public class PostBean implements Serializable {
    private static final long serialVersionUID = 1L;

    int postID;
    int userID;
    String userName;
    int star;
    String place;
    double lat;
    double lng;
    String price;
    String comment;
    String postTime;
    List<PostLikeBean> postLikeBeans;
    List<PostRespBean> postRespBeans;
    int postLikeCheck = 0; 			// 	確認用戶是否點讚？

    public PostBean() {
	super();
    }

    public PostBean(int postID, int userID, String userName, int star, String place, double lat, double lng,
	    String price, String comment, String postTime, List<PostLikeBean> postLikeBeans,
	    List<PostRespBean> postRespBeans, int postLikeCheck) {
	super();
	this.postID = postID;
	this.userID = userID;
	this.userName = userName;
	this.star = star;
	this.place = place;
	this.lat = lat;
	this.lng = lng;
	this.price = price;
	this.comment = comment;
	this.postTime = postTime;
	this.postLikeBeans = postLikeBeans;
	this.postRespBeans = postRespBeans;
	this.postLikeCheck = postLikeCheck;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public List<PostLikeBean> getPostLikeBeans() {
        return postLikeBeans;
    }

    public void setPostLikeBeans(List<PostLikeBean> postLikeBeans) {
        this.postLikeBeans = postLikeBeans;
    }

    public List<PostRespBean> getPostRespBeans() {
        return postRespBeans;
    }

    public void setPostRespBeans(List<PostRespBean> postRespBeans) {
        this.postRespBeans = postRespBeans;
    }

    public int getPostLikeCheck() {
        return postLikeCheck;
    }

    public void setPostLikeCheck(int postLikeCheck) {
        this.postLikeCheck = postLikeCheck;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}