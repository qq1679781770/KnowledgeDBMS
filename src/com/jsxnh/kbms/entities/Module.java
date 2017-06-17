package com.jsxnh.kbms.entities;

import java.util.Date;

public class Module {

	private Integer id;
	private String module;
	private String description;
	private Integer domain_id;
	private Integer create_id;
	private Date last_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(Integer domain_id) {
		this.domain_id = domain_id;
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
