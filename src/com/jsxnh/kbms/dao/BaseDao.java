package com.jsxnh.kbms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public JdbcTemplate getJdbcTemplate(){
		return this.jdbctemplate;
	}
}
