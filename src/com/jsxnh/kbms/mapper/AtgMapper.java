package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Atg;

public class AtgMapper implements RowMapper<Atg>{

	@Override
	public Atg mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Atg atg=new Atg();
		atg.setId(res.getInt("id"));
		atg.setGrade_id(res.getInt("grade_id"));
		atg.setAuthority_id(res.getInt("authority_id"));
		atg.setCreate_id(res.getInt("create_id"));
		atg.setLast_time(res.getTimestamp("last_time"));
		return atg;
	}

}
