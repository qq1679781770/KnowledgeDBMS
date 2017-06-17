package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Grade;

public class GradeMapper implements RowMapper<Grade>{

	@Override
	public Grade mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Grade grade=new Grade();
		grade.setId(res.getInt("id"));
		grade.setGrade(res.getInt("grade"));
		grade.setCreate_id(res.getInt("create_id"));
		grade.setLast_time(res.getTimestamp("last_time"));
		return grade;
	}

}
