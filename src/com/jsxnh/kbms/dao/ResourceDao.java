package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Resource;
import com.jsxnh.kbms.mapper.ResourceMapper;

@Repository
public class ResourceDao extends BaseDao{

	public void addResource(Resource resource){
		String sql="insert into resource(content,summary,create_id,domain_id,module_id,last_time) values(?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[]{resource.getContent(),resource.getSummary(),resource.getCreate_id(),
				                resource.getDomain_id(),resource.getModule_id(),resource.getLast_time()});
	}
	
	public void updateSummary(Integer id,String summary,Integer create_id){
		String sql="update resource set summary=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{summary,create_id,new Date(),id});
	}
	
	public void updateModule(Integer id,Integer module_id,Integer create_id){
		String sql="update resource set module_id=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql,new Object[]{module_id,create_id,new Date(),id});
	}
	
	public void updateDomain(Integer domain_id,Integer module_id,Integer id,Integer create_id){
		String sql="update resource set domain_id=?,module_id=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{domain_id,module_id,create_id,new Date(),id});
	}
	
	public void deleteResource(Integer id){
		String sql="delete from resource where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Resource> listAllResource(){
		String sql="select * from resource";
		return getJdbcTemplate().query(sql, new ResourceMapper());
	}
	
	
	public List<Resource> listResourceBydomain_id(Integer domain_id){
		String sql="select * from resource where domain_id=?";
		return getJdbcTemplate().query(sql, new Object[]{domain_id}, new ResourceMapper());
	}
	
	public List<Resource> listResourceBymodule_id(Integer module_id){
		String sql="select * from resource where module_id=?";
		return getJdbcTemplate().query(sql, new Object[]{module_id}, new ResourceMapper());
	}
	
	public Resource findResourceByid(Integer id){
		String sql="select * from resource where id=?";
		List<Resource>  resources=getJdbcTemplate().query(sql, new Object[]{id}, new ResourceMapper());
		if(resources.size()>0){
			return resources.get(0);
		}
		return null;
	}
}
