package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Grade;
import com.jsxnh.kbms.mapper.GradeMapper;

@Repository
public class GradeDao extends BaseDao{

	public void addGrade(Grade grade){
		String sql="insert into grade(grade,create_id,last_time) values(?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{grade.getGrade(),grade.getCreate_id(),grade.getLast_time()});
	}
	
	public void deleteGrade(Integer id){
		String sql="delete from grade where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public void updateGrade(Grade grade){
		String sql="update grade set grade=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{grade.getGrade(),grade.getCreate_id(),grade.getLast_time()
				                 ,grade.getId()});
	}
	
	public List<Grade> listAllGrade(){
		String sql="select * from grade";
		return getJdbcTemplate().query(sql, new GradeMapper());
	}
	
	public Grade findGradeBygrade(Integer grade){
		String sql="select * from grade where grade=?";
		if(getJdbcTemplate().query(sql, new Object[]{grade}, new GradeMapper()).size()>0){
			return getJdbcTemplate().query(sql, new Object[]{grade}, new GradeMapper()).get(0);
		}
		return null;
	}
	
	public Grade findGradeById(Integer id){
		String sql="select * from grade where id=?";
		if(getJdbcTemplate().query(sql, new Object[]{id}, new GradeMapper()).size()>0){
			return getJdbcTemplate().query(sql, new Object[]{id}, new GradeMapper()).get(0);
		}
		return null;
	}
}
