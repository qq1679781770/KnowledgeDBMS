package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Customer;

public class CustomerMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Customer cus=new Customer();
		cus.setId(res.getInt(1));
		cus.setPassword(res.getString(2));
		cus.setName(res.getString(3));
		cus.setAge(res.getInt(4));
		cus.setSex(res.getInt(5));
		cus.setTel(res.getInt(6));
		cus.setEmail(res.getString(7));
		cus.setGrade_id(res.getInt(8));
		cus.setDomian_id(res.getInt(9));
		cus.setImage(res.getString(10));
		return cus;
	}

	
}
