package _07_gift.model;

import java.io.Serializable;

public class RouletteBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    int giftID;
    int rouletteID;
    String name;
    String content;
    int amount;
    int point;
    double angle;

    public RouletteBean() {
	
    }

    public RouletteBean(int giftID, int rouletteID, String name, String content, int amount, int point, double angle) {
	super();
	this.giftID = giftID;
	this.rouletteID = rouletteID;
	this.name = name;
	this.content = content;
	this.amount = amount;
	this.point = point;
	this.angle = angle;
    }

    public int getGiftID() {
        return giftID;
    }

    public void setGiftID(int giftID) {
        this.giftID = giftID;
    }

    public int getRouletteID() {
        return rouletteID;
    }

    public void setRouletteID(int rouletteID) {
        this.rouletteID = rouletteID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}