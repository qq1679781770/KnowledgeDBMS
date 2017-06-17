package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Authority;

public class AuthorityMapper implements RowMapper<Authority>{

	@Override
	public Authority mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Authority authority=new Authority();
		authority.setId(res.getInt("id"));
		authority.setAuthority(res.getString("authority"));
		authority.setCreate_id(res.getInt("create_id"));
		authority.setLast_time(res.getTimestamp("last_time"));
		return authority;
	}

}
