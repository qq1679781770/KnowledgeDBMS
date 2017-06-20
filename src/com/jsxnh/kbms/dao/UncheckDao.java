package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Uncheck;
import com.jsxnh.kbms.mapper.UncheckMapper;

@Repository
public class UncheckDao extends BaseDao{

	public void addUncheck(Uncheck uncheck){
		String sql="insert into uncheck(resource,is_check,check_time,create_id,create_time,domain_id,title,module_id) values(?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, uncheck.getResource(),uncheck.getIs_check(),uncheck.getCheck_time(),
				                   uncheck.getCreate_id(),uncheck.getCreate_time(),uncheck.getDomain_id(),uncheck.getTitle(),uncheck.getModule_id());
	}
	
	public void updatecheck_time(Integer id){
		String querysql="select * from uncheck where id=?";
		List<Uncheck> unchecks=getJdbcTemplate().query(querysql, new Object[]{id},new UncheckMapper());
		Integer times=unchecks.get(0).getCheck_time();
		String sql="update uncheck set check_time=? where id=?";
		getJdbcTemplate().update(sql, times+1,id);
	}
	
	public void updateis_check(Integer id){
		String sql="update uncheck set is_check=1 where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Uncheck> listAllUncheck(){
		String sql="select * from uncheck where is_check=0";
		return getJdbcTemplate().query(sql, new UncheckMapper());
	}
	
	public List<Uncheck> findUncheckBycreate_id(Integer create_id){
		String sql="select * from uncheck where create_id=?";
		return getJdbcTemplate().query(sql, new Object[]{create_id},new UncheckMapper());
	}
}
