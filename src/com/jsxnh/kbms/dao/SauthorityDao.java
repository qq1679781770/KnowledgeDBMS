package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Sauthority;
import com.jsxnh.kbms.mapper.SauthorityMapper;

@Repository
public class SauthorityDao extends BaseDao{

	public void addSauthority(Sauthority sauthority){
		String sql="insert into sauthority values(?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{sauthority.getUser_id(),sauthority.getAuthority_id(),sauthority.getIs_grant(),
				                 sauthority.getCreate_id(),sauthority.getLast_time()});
	}
	
	public void deleteSauthority(Integer user_id,Integer authority_id){
		String sql="delete * from sauthority where user_id=? and authority_id=?";
		getJdbcTemplate().update(sql, new Object[]{user_id,authority_id});
	}
	
	public List<Sauthority> listAllSauthority(){
		String sql="select * from sauthority";
		return getJdbcTemplate().query(sql, new SauthorityMapper());
	}
	
	public List<Sauthority> listSauthorityByUser(Integer user_id){
		String sql="select * from sauthority where user_id=?";
		return getJdbcTemplate().query(sql,new Object[]{user_id},new SauthorityMapper());
	}
}
