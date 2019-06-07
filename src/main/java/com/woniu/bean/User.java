package com.woniu.bean;
import java.io.Serializable;
import java.util.Date;
public class User implements Serializable{
	private Integer uid;
	private String uname;
	private String upwd;
	private Date udate;
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", udate=" + udate + "]";
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public User(Integer uid, String uname, String upwd, Date udate) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.udate = udate;
	}
	public User() {
		super();
	}
	
	
}
