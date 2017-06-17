package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Resource;

public class ResourceMapper implements RowMapper<Resource>{

	@Override
	public Resource mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Resource resource=new Resource();
		resource.setId(res.getInt("id"));
		resource.setContent(res.getString("content"));
		resource.setCreate_id(res.getInt("create_id"));
		resource.setLast_time(res.getTimestamp("last_time"));
		resource.setDomain_id(res.getInt("domain_id"));
		resource.setModule_id(res.getInt("module_id"));
		resource.setSummary(res.getString("summary"));		
		return resource;
	}

	
}
