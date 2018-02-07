package _01_register.model;

import java.io.*;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int userID;
	String account;
	String password;
	String name;
	int point;
	String email;
	String cellphone;
	String createTime;
	String lastLoginTime;
	String birthDate;
	
	public MemberBean() {
	    super();
	}
	
	public MemberBean(String account, String password, String email, String cellphone) {
	    super();
	    this.account = account;
	    this.password = password;
	    this.email = email;
	    this.cellphone = cellphone;
	    // 預設名字與帳號相同。
	    this.name = account;
	}

	public MemberBean(int userID, String account, String password, String name, int point, String email,
		String cellphone, String createTime, String lastLoginTime, String birthDate) {
	    super();
	    this.userID = userID;
	    this.account = account;
	    this.password = password;
	    this.name = name;
	    this.point = point;
	    this.email = email;
	    this.cellphone = cellphone;
	    this.createTime = createTime;
	    this.lastLoginTime = lastLoginTime;
	    this.birthDate = birthDate;
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

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
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

	public String getCellphone() {
	    return cellphone;
	}

	public void setCellphone(String cellphone) {
	    this.cellphone = cellphone;
	}

	public String getCreateTime() {
	    return createTime;
	}

	public void setCreateTime(String createTime) {
	    this.createTime = createTime;
	}

	public String getLastLoginTime() {
	    return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
	    this.lastLoginTime = lastLoginTime;
	}

	public String getBirthDate() {
	    return birthDate;
	}

	public void setBirthDate(String birthDate) {
	    this.birthDate = birthDate;
	}

	public static long getSerialversionuid() {
	    return serialVersionUID;
	}
	
}
