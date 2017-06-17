package com.jsxnh.kbms.entities;

import java.util.Date;

public class Sauthority {

	private Integer user_id;
	private Integer authority_id;
	private Integer is_grant;
	private Integer create_id;
	private Date last_time;
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAuthority_id() {
		return authority_id;
	}
	public void setAuthority_id(Integer authority_id) {
		this.authority_id = authority_id;
	}
	public Integer getIs_grant() {
		return is_grant;
	}
	public void setIs_grant(Integer is_grant) {
		this.is_grant = is_grant;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	public Date getLast_time() {
		return last_time;
	}
	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}
	
}
