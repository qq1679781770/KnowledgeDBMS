package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Rattribute;
import com.jsxnh.kbms.mapper.RattributeMapper;

@Repository
public class RattributeDao extends BaseDao{
	
	public void addRattribute(Rattribute rattribute){
		String sql="insert into rattribute(resource_id,attribute_id,value,create_id,last_time) values(?,?,?,?,?)";
		getJdbcTemplate().update(sql, rattribute.getResource_id(),rattribute.getAttribute_id(),rattribute.getValue(),
				                 rattribute.getCreate_id(),rattribute.getLast_time());
	}
	
	public void updateAttribute_id(Integer attribute_id,Integer id,Integer create_id){
		String sql="update rattribute set attribute_id=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, attribute_id,create_id,new Date(),id);
	}
	
	public void updateValue(String value,Integer create_id,Integer id){
		String sql="update rattribute set value=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, value,create_id,new Date(),id);
	}
	
	public void deleteRattribute(Integer id){
		String sql="delete * from where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Rattribute> findRattributeByResource(Integer resource_id){
		String sql="select * from where resource_id=?";
		return getJdbcTemplate().query(sql, new Object[]{resource_id},new RattributeMapper());
	}
	
	public List<Rattribute> listAllRattribute(){
		String sql="select * from rattribute";
		return getJdbcTemplate().query(sql, new RattributeMapper());
	}

}
