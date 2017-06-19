package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Atg;
import com.jsxnh.kbms.mapper.AtgMapper;

@Repository
public class AtgDao extends BaseDao{

	public void addAtg(Atg atg){
		String sql="insert into atg(grade_id,authority_id,create_id,last_time) values(?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{atg.getGrade_id(),atg.getAuthority_id(),atg.getCreate_id(),atg.getLast_time()});
	}
	
	public void updateAtg(Atg atg){
		String sql="update atg set grade_id=?,authority=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{atg.getGrade_id(),atg.getAuthority_id(),atg.getCreate_id()
				                 ,atg.getLast_time(),atg.getId()}); 
	}
	
	public void deleteAtg(Integer id){
		String sql="delete  from atg where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Atg> listAllAtg(){
		String sql="select * from atg";
		return getJdbcTemplate().query(sql, new AtgMapper());
	}
	
	public List<Atg> findAtgBygrade(Integer grade_id){
		String sql="select * from atg where grade_id=?";
		return getJdbcTemplate().query(sql, new Object[]{grade_id},new AtgMapper());
	}
}
