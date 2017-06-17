package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Checkres;
import com.jsxnh.kbms.mapper.CheckresMapper;

@Repository
public class CheckresDao extends BaseDao{

	public void addCheckres(Checkres checkres){
		String sql="insert into checkres(check_id,score,res) values(?,?,?)";
		getJdbcTemplate().update(sql, checkres.getCheck_id(),checkres.getScore(),checkres.getRes());
	}
	
	public Checkres findBycheck_id(Integer check_id){
		String sql="select * from checkres where check_id=?";
		List<Checkres> checkress=getJdbcTemplate().query(sql, new Object[]{check_id}, new CheckresMapper());
		if(checkress.size()>0){
			return checkress.get(0);
		}
		return null;
	}
}
