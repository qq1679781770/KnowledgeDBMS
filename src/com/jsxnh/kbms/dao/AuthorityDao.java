package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.mapper.AuthorityMapper;

@Repository
public class AuthorityDao extends BaseDao{

	public void addAuthority(Authority authority){
		String  sql="insert into authority(authority,create_id,last_time) values(?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{authority.getAuthority(),authority.getCreate_id(),authority.getLast_time()});
	}
	
	public void deleteAuthority(Integer id){
		String sql="delete * from authority where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public void updateAuthority(Authority authority){
		String sql="update authority set authority=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql,new Object[]{authority.getAuthority(),authority.getCreate_id(),authority.getLast_time()
				                 ,authority.getId()});
	}
	
	public List<Authority> listAllAuthority(){
		String sql="select * from authority";
		return getJdbcTemplate().query(sql,new AuthorityMapper());
	}
	
}
