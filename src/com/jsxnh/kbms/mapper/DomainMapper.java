package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Domain;

public class DomainMapper implements RowMapper<Domain>{

	@Override
	public Domain mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Domain domain=new Domain();
		domain.setId(res.getInt("id"));
		domain.setDescription(res.getString("description"));
		domain.setDomain(res.getString("domain"));
		domain.setCreate_id(res.getInt("create_id"));
		domain.setLast_time(res.getTimestamp("last_time"));
		
		return domain;
	}

}
