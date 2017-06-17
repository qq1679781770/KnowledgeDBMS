package com.jsxnh.kbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jsxnh.kbms.entities.Rattribute;

public class RattributeMapper implements RowMapper<Rattribute>{

	@Override
	public Rattribute mapRow(ResultSet res, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Rattribute rattribute=new Rattribute();
		rattribute.setId(res.getInt("id"));
		rattribute.setAttribute_id(res.getInt("attribute_id"));
		rattribute.setResource_id(res.getInt("resource_id"));
		rattribute.setValue(res.getString("value"));
		rattribute.setCreate_id(res.getInt("create_id"));
		rattribute.setLast_time(res.getTimestamp("last_time"));
		return rattribute;
	}

}
