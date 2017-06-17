package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Sauthority;

public class SauthorityMapper implements RowMapper<Sauthority>{

	@Override
	public Sauthority mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Sauthority sauthority=new Sauthority();
		sauthority.setUser_id(res.getInt("user_id"));
		sauthority.setAuthority_id(res.getInt("authority_id"));
		sauthority.setIs_grant(res.getInt("is_grant"));
		sauthority.setCreate_id(res.getInt("create_id"));
		sauthority.setLast_time(res.getTimestamp("last_time"));
		return sauthority;
	}

}
