package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Attribute;
import com.jsxnh.kbms.mapper.AttributeMapper;

@Repository
public class AttributeDao extends BaseDao{

	public void addAttribute(Attribute attribute){
		String sql="insert into attribute(attribute,create_id,last_time) values(?,?,?)";
		getJdbcTemplate().update(sql, attribute.getAttribute(),attribute.getCreate_id(),attribute.getLast_time());
	}
	
	public void updateAttribute(String attribute,Integer id,Integer create_id){
		String sql="update attribute set attribute=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, attribute,create_id,new Date(),id);
	}
	
	public void deleteAttribute(Integer id){
		String sql="delete * from attribute where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Attribute> listAllAttribute(){
		String sql="select * from attribute";
		return getJdbcTemplate().query(sql, new AttributeMapper());
	}
	
	public List<Attribute> findAttributebyId(Integer id){
		String sql="select * from attribute where id=?";
		return getJdbcTemplate().query(sql, new AttributeMapper());
	}
}
