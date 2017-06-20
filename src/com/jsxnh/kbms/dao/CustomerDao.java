package com.jsxnh.kbms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsxnh.kbms.entities.Customer;
import com.jsxnh.kbms.mapper.CustomerMapper;

@Repository
public class CustomerDao extends BaseDao{

	public void addCustomer(Customer cus){
		String sql="insert into customer values(?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(sql, cus.getId(),cus.getPassword(),cus.getName(),cus.getAge(),cus.getSex(),cus.getTel(),cus.getEmail()
				,cus.getGrade_id(),cus.getDomian_id(),cus.getImage());
	}
	
	public List<Customer> queryForExist(Integer id){
		String sql="select * from customer where id=?";
		return getJdbcTemplate().query(sql,new Object[]{id},new CustomerMapper());
	}
	public boolean modifyPassword(Integer id,Integer tel,String password){
		String querysql="select * from customer where id=? and tel=?";
		List<Customer> cus=getJdbcTemplate().query(querysql, new Object[]{id,tel},new CustomerMapper());
		if(cus.size()==0){
			return false;
		}
		String updatesql="update customer set password=? where id=?";
		getJdbcTemplate().update(updatesql, new Object[]{password,id});
		return true;
	}
	
	public void updateGrade(Integer id,Integer grade_id){
		String sql="update customer set grade_id=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{grade_id,id});
	}
	
	public void updateDomain(Integer id,Integer domain_id){
		String sql="update customer set domain_id=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{domain_id,id});
	}
	
	public void updateMessage(Integer id,String name,Integer sex,Integer age){
		String sql="update customer set name=?,sex=?,age=? where id=?";
		getJdbcTemplate().update(sql, new Object[]{name,sex,age,id});
	}
	
	public void deleteCustomer(Integer id){
		String sql="delete  from customer where id=?";
		getJdbcTemplate().update(sql, id);
	}
	
	
	public boolean Login(Integer id,String password){
		String sql="select * from customer where id=? and password=?";
		if(getJdbcTemplate().query(sql, new Object[]{id, password},new CustomerMapper()).size()>0){
			return true;
		}
		return false;
	}
	
	public List<Customer> listAllusers(){
		String sql="select * from customer";
		return getJdbcTemplate().query(sql, new CustomerMapper());
	}
}
