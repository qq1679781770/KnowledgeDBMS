package com.jsxnh.kbms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Relation;
import com.jsxnh.kbms.mapper.RelationMapper;

@Repository
public class RelationDao extends BaseDao{

	public void addRelation(Relation relation){
		String sql="insert into relation(relation.description.create_id,last_time) values(?,?,?,?)";
		getJdbcTemplate().update(sql, relation.getRelation(),relation.getDescription(),relation.getCreate_id(),relation.getLast_time());
	}
	
	public void updateRealtion(String relation,Integer create_id,Integer id){
		String sql="update relation set relation=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, relation,create_id,new Date(),id);
	}
	
	public void updateDescription(String description,Integer create_id,Integer id){
		String sql="update relation set description=?,create_id=?,last_time=? where id=?";
		getJdbcTemplate().update(sql, description,create_id,new Date(),id);
	}
	
	public void deleteRelation(Integer id){
		String sql="delete  from relation where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	public List<Relation> listAllRelation(){
		String sql="select * from relation";
		return getJdbcTemplate().query(sql, new RelationMapper());
	}
}
