package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Module;

public class ModuleMapper implements RowMapper<Module>{

	@Override
	public Module mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Module module=new Module();
		module.setId(res.getInt("id"));
		module.setDomain_id(res.getInt("domain_id"));
		module.setCreate_id(res.getInt("create_id"));
		module.setDescription(res.getString("description"));
		module.setModule(res.getString("module"));
		module.setLast_time(res.getTimestamp("last_time"));
		return module;
	}

	
	
}
