package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Relation;

public class RelationMapper implements RowMapper<Relation>{

	@Override
	public Relation mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Relation relation=new Relation();
		relation.setId(res.getInt("id"));
		relation.setRelation(res.getString("relation"));
		relation.setDescription(res.getString("description"));
		relation.setCreate_id(res.getInt("create_id"));
		relation.setLast_time(res.getTimestamp("last_time"));
		return relation;
	}

}
