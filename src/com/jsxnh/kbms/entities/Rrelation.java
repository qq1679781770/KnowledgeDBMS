package com.jsxnh.kbms.entities;

import java.util.Date;

public class Rrelation {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer subjectresource_id;
	private Integer relation_id;
	private Integer objectresource_id;
	private Integer create_id;
	private Date last_time;
	
	public Integer getSubjectresource_id() {
		return subjectresource_id;
	}
	public void setSubjectresource_id(Integer subjectresource_id) {
		this.subjectresource_id = subjectresource_id;
	}
	public Integer getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	public Integer getObjectresource_id() {
		return objectresource_id;
	}
	public void setObjectresource_id(Integer objectresource_id) {
		this.objectresource_id = objectresource_id;
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
