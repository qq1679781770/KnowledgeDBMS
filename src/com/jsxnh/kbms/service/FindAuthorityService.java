package com.jsxnh.kbms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsxnh.kbms.dao.AtgDao;
import com.jsxnh.kbms.dao.AuthorityDao;
import com.jsxnh.kbms.dao.CustomerDao;
import com.jsxnh.kbms.dao.SauthorityDao;
import com.jsxnh.kbms.entities.Atg;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Customer;
import com.jsxnh.kbms.entities.Sauthority;

@Service
public class FindAuthorityService {

	@Autowired
	CustomerDao customerDao;
	@Autowired
	AuthorityDao authorityDao;
	@Autowired
	SauthorityDao sauthorityDao;
	@Autowired
	AtgDao atgDao;
	
	public List<Authority> findAuthority(Integer user_id){
		Customer cus=customerDao.queryForExist(user_id).get(0);
		List<Atg> atgs=atgDao.findAtgBygrade(cus.getGrade_id());
		List<Authority> authorities=new LinkedList<>();
		for(Atg atg:atgs){
			authorities.add(authorityDao.findAuthorityByid(atg.getAuthority_id()));
		}
		for(Sauthority sauthority:sauthorityDao.listSauthorityByUser(user_id)){
			if(sauthority.getIs_grant()==1){
				if(authorities.contains(authorityDao.findAuthorityByid(sauthority.getAuthority_id()))){
					continue;
				}
				authorities.add(authorityDao.findAuthorityByid(sauthority.getAuthority_id()));
			}else{
				if(!authorities.contains(authorityDao.findAuthorityByid(sauthority.getAuthority_id()))){
					continue;
				}
				authorities.remove(authorityDao.findAuthorityByid(sauthority.getAuthority_id()));
			}
		}
		return authorities;
	}
}
