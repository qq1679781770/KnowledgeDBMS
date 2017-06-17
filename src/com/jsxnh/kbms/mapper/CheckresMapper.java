package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Checkres;

public class CheckresMapper implements RowMapper<Checkres>{

	@Override
	public Checkres mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Checkres checkres=new Checkres();
		checkres.setCheck_id(res.getInt("check_id"));
		checkres.setRes(res.getInt("res"));
		checkres.setScore(res.getInt("score"));
		return null;
	}

}
