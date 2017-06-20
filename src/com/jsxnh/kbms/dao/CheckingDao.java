package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Checking;
import com.jsxnh.kbms.mapper.CheckingMapper;

@Repository
public class CheckingDao extends BaseDao{

	public void addChecking(Checking checking){
		String sql="insert into checking values(?,?,?,?)";
		getJdbcTemplate().update(sql, checking.getCheck_id(),checking.getUser_id(),checking.getScore(),checking.getTime());
	}
	
	public List<Checking> findcheckingBycheck_id(Integer check_id){
		String sql="select * from checking where check_id=?";
		return getJdbcTemplate().query(sql, new Object[]{check_id}, new CheckingMapper());
	}
	
	public List<Checking> findcheckingByuser_id(Integer user_id){
		String sql="select * from checking where user_id=?";
		return getJdbcTemplate().query(sql, new Object[]{user_id},new CheckingMapper());
	}
	
	public List<Checking> listAllChecking(){
		String sql="select * from checking";
		return getJdbcTemplate().query(sql, new CheckingMapper());
	}
	
	
	public boolean existUncheck(Integer check_id,Integer user_id){
		String sql="select * from checking where user_id=? and check_id=?";
		if(getJdbcTemplate().query(sql, new Object[]{user_id,check_id},new CheckingMapper()).size()==0){
			return false;
		}else{
			return true;
		}
	}
}
