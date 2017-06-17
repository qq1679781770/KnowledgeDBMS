package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Uncheck;

public class UncheckMapper implements RowMapper<Uncheck>{

	@Override
	public Uncheck mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Uncheck uncheck=new Uncheck();
		uncheck.setId(res.getInt("id"));
		uncheck.setDomain_id(res.getInt("domain_id"));
		uncheck.setCreate_id(res.getInt("create_id"));
		uncheck.setCreate_time(res.getTimestamp("create_time"));
		uncheck.setCheck_time(res.getInt("check_time"));
		uncheck.setIs_check(res.getInt("is_check"));
		uncheck.setResource(res.getString("resource"));
		return uncheck;
	}

}
