package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Rrelation;
import com.jsxnh.kbms.mapper.RrelationMapper;

@Repository
public class RrelationDao extends BaseDao{

	public void addRrelation(Rrelation rrelation){
		String sql="insert into rrelation(subjectresource_id,relation_id,objectresource_id,create_id,last_time) values(?,?,?,?,?)";
		getJdbcTemplate().update(sql, rrelation.getSubjectresource_id(),rrelation.getRelation_id(),rrelation.getObjectresource_id(),
				                 rrelation.getCreate_id(),rrelation.getLast_time());
	}
	
	public void updateRrelation(Integer relation_id,Integer create_id,Integer id){
		String sql="update rrelation set relation_id=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, relation_id,create_id,new Date(),id);
	}
	
	public void deleteRrelation(Integer id){
		String sql="delete * from rrelation where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Rrelation> listAllRrelation(){
		String sql="select *from rrelation";
		return getJdbcTemplate().query(sql, new RrelationMapper());
	}
	
	public List<Rrelation> listRrelationbysubject(Integer subjectresource_id){
		String sql="select * from rrelation where subjectresource_id";
		return getJdbcTemplate().query(sql, new Object[]{subjectresource_id},new RrelationMapper());
	}
	
	public List<Rrelation> listRrelationbyobject(Integer objectresource_id){
		String sql="select * from rrelation where objectresource_id=?";
		return getJdbcTemplate().query(sql, new Object[]{objectresource_id},new RrelationMapper());
	}
}
