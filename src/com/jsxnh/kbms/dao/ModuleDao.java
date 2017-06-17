package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Module;
import com.jsxnh.kbms.mapper.ModuleMapper;

@Repository
public class ModuleDao extends BaseDao{

	public void addModule(Module module){
		String sql="insert into module(module,description,domain_id,create_id,last_time) values(?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{module.getModule(),module.getDescription(),module.getDomain_id(),
				                 module.getCreate_id(),module.getLast_time()}); 
	}
	
	public void updateModule(String module,Integer create_id,Integer id){
		String sql="update module set module=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, module,create_id,new Date(),id);
	}
	
	public void updateDescription(String description,Integer create_id,Integer id){
		String sql="update module set description=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, description,create_id,new Date(),id);
	}
	
	public void updateDomain(Integer domain_id,Integer create_id,Integer id){
		String sql="update module set domain_id=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, domain_id,create_id,new Date(),id);
	}
	
	public void deleteModule(Integer id){
		String sql="delete * from module where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Module> listAllModule(){
		String sql="select * from module";
		return getJdbcTemplate().query(sql, new ModuleMapper());
	}
}
