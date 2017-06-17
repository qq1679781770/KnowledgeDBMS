package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Checking;

public class CheckingMapper implements RowMapper<Checking>{

	@Override
	public Checking mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Checking checking=new Checking();
		checking.setCheck_id(res.getInt("check_id"));
		checking.setUser_id(res.getInt("user_id"));
		checking.setScore(res.getInt("score"));
		checking.setTime(res.getTimestamp("time"));
		return null;
	}

}
