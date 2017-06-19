package com.jsxnh.kbms.entities;

import java.util.Date;

public class Uncheck {

	private Integer id;
	private String resource;
	private Integer domain_id;
	private Integer is_check;
	private Integer check_time;
	private Integer create_id;
	private Date create_time;
	private String title;
	private Integer module_id;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(Integer domain_id) {
		this.domain_id = domain_id;
	}
	public Integer getIs_check() {
		return is_check;
	}
	public void setIs_check(Integer is_check) {
		this.is_check = is_check;
	}
	public Integer getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Integer check_time) {
		this.check_time = check_time;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
