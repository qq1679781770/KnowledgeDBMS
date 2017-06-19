package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Domain;
import com.jsxnh.kbms.mapper.DomainMapper;

@Repository
public class DomainDao extends BaseDao{

	public void addDomain(Domain domain){
		String sql="insert into domain(domain,description,create_id,last_time) values(?,?,?,?)";
		getJdbcTemplate().update(sql, domain.getDomain(),domain.getDescription(),domain.getCreate_id(),domain.getLast_time());
	}
	
	public void updateDomain(String domain,Integer create_id,Integer id){
		String sql="update domain set domain=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql,domain,create_id,new Date(),id);
	}
	
	public void updateDescription(String description,Integer create_id,Integer id){
		String sql="update domain set description=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, description,create_id,new Date(),id);
	}
	
	public void deleteDomain(Integer id){
		String sql="delete  from domain where id =?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Domain> listAllDomain(){
		String sql="select * from domain";
		return getJdbcTemplate().query(sql, new DomainMapper());
	}
	
	public Domain finddomainByid(Integer id){
		String sql="select * from domain where id=?";
		List<Domain> domains=getJdbcTemplate().query(sql, new Object[]{id},new DomainMapper());
		if(domains.size()>0){
			return domains.get(0);
		}
		return null;
	}
	
	public Domain finddomainBydomain(String domain){
		String sql="select * from domain where domain=?";
		List<Domain> domains=getJdbcTemplate().query(sql, new Object[]{domain},new DomainMapper());
		if(domains.size()>0){
			return domains.get(0);
		}
		return null;
	}
}
