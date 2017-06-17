package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Attribute;

public class AttributeMapper implements RowMapper<Attribute>{

	@Override
	public Attribute mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Attribute attribute=new Attribute();
		attribute.setId(res.getInt("id"));
		attribute.setAttribute(res.getString("attribute"));
		attribute.setCreate_id(res.getInt("create_id"));
		attribute.setLast_time(res.getTimestamp("last_time"));
		return attribute;
	}

	
}
