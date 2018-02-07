package _07_gift.model;

import java.io.Serializable;

public class GiftBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    int giftID;
    String name;
    String content;
    int point;
    int status;
    int amount;
    int probability;
    
    public GiftBean(){
    }

    public GiftBean(int giftID, String name, String content, int point, int status, int amount, int probability) {
	super();
	this.giftID = giftID;
	this.name = name;
	this.content = content;
	this.point = point;
	this.status = status;
	this.amount = amount;
	this.probability = probability;
    }

    public int getGiftID() {
        return giftID;
    }

    public void setGiftID(int giftID) {
        this.giftID = giftID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}