package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Rrelation;

public class RrelationMapper implements RowMapper<Rrelation>{

	@Override
	public Rrelation mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Rrelation rrelation=new Rrelation();
		rrelation.setId(res.getInt("id"));
		rrelation.setSubjectresource_id(res.getInt("subjectresource_id"));
		rrelation.setRelation_id(res.getInt("relation_id"));
		rrelation.setObjectresource_id(res.getInt("objectresource_id"));
		rrelation.setCreate_id(res.getInt("create_id"));
		rrelation.setLast_time(res.getTimestamp("last_time"));
		return rrelation;
	}

}
